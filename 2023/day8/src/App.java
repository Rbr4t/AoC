import java.io.*;
import java.util.*;

public class App {

    static int part_1(String instructions, ArrayList<HashMap<String, ArrayList<String>>> map) {
        int counter = 0;
        int mapIndex = 0;
        HashMap<String, ArrayList<String>> el = map.get(0);
        String current = "AAA";
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).keySet().toArray()[0].toString().equals(current)) {
                mapIndex = i;
                break;
            }
        }

        while (!current.equals("ZZZ")) {
            el = map.get(mapIndex);
            if (instructions.charAt(counter % instructions.length()) == 'L') {
                current = el.get(el.keySet().toArray()[0]).get(0);
            } else {
                current = el.get(el.keySet().toArray()[0]).get(1);
            }

            for (int i = 0; i < map.size(); i++) {
                if (map.get(i).keySet().toArray()[0].toString().equals(current)) {
                    mapIndex = i;
                    break;
                }
            }

            counter++;
        }

        return counter;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("/home/rbr4t/AoC/2023/day8/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<HashMap<String, ArrayList<String>>> map = new ArrayList<>();
        String st;
        String instructions = "";
        while ((st = br.readLine()) != null) {
            if (!st.contains("=")) {
                instructions = st;
            } else {
                HashMap<String, ArrayList<String>> newMap = new HashMap();
                ArrayList<String> destinations = new ArrayList<>();
                destinations.add(st.split("=")[1].substring(2, 10).split(", ")[0].toString());
                destinations.add(st.split("=")[1].substring(2, 10).split(", ")[1].toString());

                newMap.put(st.split("=")[0].trim(), destinations);
                map.add(newMap);
            }
        }

        System.out.println(part_1(instructions, map));
        br.close();
    }
}
