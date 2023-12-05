import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class App {

    static long part_1(long seed, ArrayList<HashMap<Long, long[]>> categories) {
        long dest = (long) seed;
        for (HashMap<Long, long[]> category : categories) {
            // System.out.println(category.values());
            int i = 0;

            for (long[] v : category.values()) {
                if (dest >= v[0] && dest < v[1]) {
                    dest = (long) category.keySet().toArray()[i] + (dest - v[0]);
                    break;
                }
                i++;
            }

        }

        return dest;
    }

    static class Part2Task implements Callable<Long> {
        private final long seed;
        private final ArrayList<HashMap<Long, long[]>> categories;

        public Part2Task(long seed, ArrayList<HashMap<Long, long[]>> categories) {
            this.seed = seed;
            this.categories = categories;
        }

        @Override
        public Long call() {
            return part_1(seed, categories);
        }
    }

    public static void main(String[] args) throws Exception {
        File file = new File("/home/rbr4t/AoC/2023/day5/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        ArrayList<Long> seeds = new ArrayList<>();

        HashMap<Long, long[]> seedToSoil = new HashMap<>();
        HashMap<Long, long[]> soilToFertilizer = new HashMap<>();
        HashMap<Long, long[]> fertilizerToWater = new HashMap<>();
        HashMap<Long, long[]> waterToLight = new HashMap<>();
        HashMap<Long, long[]> lightToTemperature = new HashMap<>();
        HashMap<Long, long[]> temperatureToHumidity = new HashMap<>();
        HashMap<Long, long[]> humidityToLocation = new HashMap<>();

        ArrayList<HashMap<Long, long[]>> categories = new ArrayList<>();
        categories.add(seedToSoil);
        categories.add(soilToFertilizer);
        categories.add(fertilizerToWater);
        categories.add(waterToLight);
        categories.add(lightToTemperature);
        categories.add(temperatureToHumidity);
        categories.add(humidityToLocation);

        int currentCategoryIndex = -1;

        while ((st = br.readLine()) != null) {
            // parse seeds and maps
            // generate hashmaps with destinations and ranges

            if (st.indexOf("seeds") > -1) {
                for (String num : st.split(":")[1].trim().split(" ")) {
                    seeds.add(Long.parseLong(num));
                }

            } else {
                // if the line is new category
                if (st.indexOf("map") > -1) {
                    currentCategoryIndex++;
                } else if (!st.isBlank()) {
                    // if the line is map
                    // System.out.println(Arrays.toString(st.split(" ")));
                    long dest = Long.parseLong(st.split(" ")[0].trim());
                    long start = Long.parseLong(st.trim().split(" ")[1]);
                    long end = start + Long.parseLong(st.trim().split(" ")[2]);
                    long[] boundaries = { start, end };
                    categories.get(currentCategoryIndex).put(dest, boundaries);
                }

            }

        }

        // part 1
        // for (Long seed : seeds) {
        // System.out.println("seed is: " + seed);

        // long newLocation = part_1(seed, categories);
        // if (newLocation < location) {
        // location = newLocation;
        // }

        // }
        long location = Long.MAX_VALUE;
        // part 2

        // part 2 multithreaded
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Long>> futures = new ArrayList<>();
        List<List<Long>> optimizedSeeds = new ArrayList<>();
        for (int i = 0; i < seeds.size(); i = i + 2) {
            optimizedSeeds.add(List.of(seeds.get(i), seeds.get(i + 1)));

        }
        Collections.sort(optimizedSeeds, new Comparator<List<Long>>() {
            @Override
            public int compare(List<Long> list1, List<Long> list2) {
                // Compare based on the first element
                return list1.get(0).compareTo(list2.get(0));
            }
        });

        for (int k = 0; k < optimizedSeeds.size(); k++) {
            long start = optimizedSeeds.get(k).get(0);
            long end = optimizedSeeds.get(k).get(0) + optimizedSeeds.get(k).get(1);

            System.out.println("Pair: " + k);
            for (long i = start; i < end; i++) {
                futures.add(executorService.submit(new Part2Task(i, categories)));
                if (futures.size() > 40000000) {
                    for (Future<Long> future : futures) {
                        try {
                            long newLocation = future.get();

                            if (newLocation < location) {
                                location = newLocation;
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                            System.out.println(i);
                            System.out.println(location);
                        }

                    }
                    futures.clear();

                }
            }

        }

        executorService.shutdown();

        System.out.println(location);

        br.close();

    }
}
