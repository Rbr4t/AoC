import java.io.*;
import java.util.*;

public class App {
    static class SortHands implements Comparator<HashMap<String, Integer>> {
        final String order = "23456789TJQKA";

        @Override
        public int compare(HashMap<String, Integer> o1, HashMap<String, Integer> o2) {
            String value1 = o1.keySet().toArray()[0].toString();
            String value2 = o2.keySet().toArray()[0].toString();
            for (int i = 0; i < 5; i++) {

                if (order.indexOf(value1.charAt(i)) > order.indexOf(value2.charAt(i))) {

                    return -1;
                } else if (order.indexOf(value1.charAt(i)) < order.indexOf(value2.charAt(i))) {
                    return 1;
                }
            }
            return 0;

        }

    }

    static String identifyRank(String card) {
        HashMap<Character, Integer> occurrences = new HashMap<>();
        for (int i = 0; i < card.length(); i++) {
            occurrences.put(card.charAt(i), occurrences.getOrDefault(card.charAt(i), 0) + 1);
        }

        String type;

        switch (Collections.max(occurrences.values())) {
            case 5:
                type = "Five of a kind";
                break;
            case 4:
                type = "Four of a kind";
                break;
            case 3:
                occurrences.values().remove(3);
                if (occurrences.values().remove(2)) {
                    type = "Full house";
                } else {
                    type = "Three of a kind";

                }
                break;
            case 2:
                occurrences.values().remove(2);
                if (occurrences.values().remove(2)) {
                    type = "Two pair";
                } else {
                    type = "One pair";
                }
                break;
            default:
                type = "High card";
                break;
        }
        return type;
    }

    static long part_1(ArrayList<HashMap<String, Integer>> cards) {
        List<LinkedHashMap<String, List<HashMap<String, Integer>>>> groupedCards = new ArrayList<>();
        for (HashMap<String, Integer> card : cards) {
            HashMap<String, Integer> newEntry = new HashMap<>();

            String type = identifyRank(card.keySet().toArray()[0].toString());
            newEntry.put(card.keySet().toArray()[0].toString(), (int) card.values().toArray()[0]);

            boolean found = false;
            for (int i = 0; i < groupedCards.size(); i++) {
                if (groupedCards.get(i).containsKey(type)) {
                    List<HashMap<String, Integer>> list = groupedCards.get(i).get(type);
                    list.add(newEntry);
                    found = true;
                    break;
                }
            }

            if (!found) {
                LinkedHashMap<String, List<HashMap<String, Integer>>> group = new LinkedHashMap<>();
                List<HashMap<String, Integer>> list = new ArrayList<>(Collections.singletonList(newEntry));
                group.put(type, list);
                groupedCards.add(group);
            }
        }

        for (LinkedHashMap<String, List<HashMap<String, Integer>>> group : groupedCards) {
            for (List<HashMap<String, Integer>> innerList : group.values()) {

                Collections.sort(innerList, new SortHands());
            }
        }

        List<HashMap<String, Integer>> flatList = new ArrayList<>();
        String[] categoriesOrder = { "Five of a kind", "Four of a kind", "Full house", "Three of a kind", "Two pair",
                "One pair",
                "High card" };

        for (String category : categoriesOrder) {
            for (LinkedHashMap<String, List<HashMap<String, Integer>>> group : groupedCards) {
                if (group.containsKey(category)) {

                    flatList.addAll(group.get(category));

                }
            }
        }

        long sum = 0;
        for (int i = 0; i < flatList.size(); i++) {

            sum += flatList.get(i).get(flatList.get(i).keySet().toArray()[0]) * (flatList.size() - i);
        }

        return sum;

    }

    static String identifyRankJokers(String card) {
        HashMap<Character, Integer> occurrences = new HashMap<>();
        for (int i = 0; i < card.length(); i++) {
            occurrences.put(card.charAt(i), occurrences.getOrDefault(card.charAt(i), 0) + 1);
        }

        String type;

        if (occurrences.get("J") > 1) {

        }

        switch (Collections.max(occurrences.values())) {
            case 5:
                type = "Five of a kind";
                break;
            case 4:
                type = "Four of a kind";
                break;
            case 3:
                occurrences.values().remove(3);
                if (occurrences.values().remove(2)) {
                    type = "Full house";
                } else {
                    type = "Three of a kind";

                }
                break;
            case 2:
                occurrences.values().remove(2);
                if (occurrences.values().remove(2)) {
                    type = "Two pair";
                } else {
                    type = "One pair";
                }
                break;
            default:
                type = "High card";
                break;
        }
        return type;
    }

    static long part_2(ArrayList<HashMap<String, Integer>> cards) {
        List<LinkedHashMap<String, List<HashMap<String, Integer>>>> groupedCards = new ArrayList<>();
        for (HashMap<String, Integer> card : cards) {
            HashMap<String, Integer> newEntry = new HashMap<>();

            String type = identifyRank(card.keySet().toArray()[0].toString());
            newEntry.put(card.keySet().toArray()[0].toString(), (int) card.values().toArray()[0]);

            boolean found = false;
            for (int i = 0; i < groupedCards.size(); i++) {
                if (groupedCards.get(i).containsKey(type)) {
                    List<HashMap<String, Integer>> list = groupedCards.get(i).get(type);
                    list.add(newEntry);
                    found = true;
                    break;
                }
            }

            if (!found) {
                LinkedHashMap<String, List<HashMap<String, Integer>>> group = new LinkedHashMap<>();
                List<HashMap<String, Integer>> list = new ArrayList<>(Collections.singletonList(newEntry));
                group.put(type, list);
                groupedCards.add(group);
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("/home/rbr4t/AoC/2023/day7/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        ArrayList<HashMap<String, Integer>> unsortedCards = new ArrayList<>();
        while ((st = br.readLine()) != null) {
            HashMap<String, Integer> card = new HashMap<>();
            card.put(st.split(" ")[0], Integer.parseInt(st.split(" ")[1]));
            unsortedCards.add(card);
        }

        System.out.println(part_1(unsortedCards));
        br.close();
    }
}
