import java.io.*;
import java.util.*;

public class Day1 {

    public int part_1(String str) {
        String num = "";

        int i = 0;
        int j = str.length() - 1;
        boolean[] run = { true, true };

        while (run[0] || run[1]) {
            if (run[0]) {
                if (Character.isDigit(str.charAt(i))) {
                    num += str.charAt(i);
                    run[0] = false;
                } else {
                    i++;
                }
            } else if (run[1]) {
                if (Character.isDigit(str.charAt(j))) {
                    num += str.charAt(j);
                    run[1] = false;
                } else {
                    j--;
                }
            }

        }

        return Integer.parseInt(num);
    }

    public static int part_2(String str) {

        int i = 0;
        int j = str.length() - 1;

        Hashtable<String, Integer> dict = new Hashtable<>();
        dict.put("one", 1);
        dict.put("two", 2);
        dict.put("three", 3);
        dict.put("four", 4);
        dict.put("five", 5);
        dict.put("six", 6);
        dict.put("seven", 7);
        dict.put("eight", 8);
        dict.put("nine", 9);

        Enumeration enu = dict.keys();

        Hashtable<String, Integer> positions = new Hashtable<>();

        // first I will look for numbers spelled with letters
        for (int k = 0; k < 9; k++) {
            String el = enu.nextElement().toString();
            if (str.contains(el)) {

                positions.put(dict.get(el) + "", str.indexOf(el));
            }

        }
        // secondly, I will look for numbers in a digit form
        while (i < j) {

            if (Character.isDigit(str.charAt(i))) {
                if (!positions.containsKey(String.valueOf(str.charAt(i)))) {
                    positions.put(String.valueOf(str.charAt(i)), i);
                } else if (positions.get(String.valueOf(str.charAt(i))) > i) {
                    positions.put(String.valueOf(str.charAt(i)), i);
                }

            }

            if (Character.isDigit(str.charAt(j))) {
                if (!positions.containsKey(String.valueOf(str.charAt(j)))) {

                    positions.put(String.valueOf(str.charAt(j)), j);
                } else if (positions.get(String.valueOf(str.charAt(j))) > j) {
                    positions.put(String.valueOf(str.charAt(j)), j);
                }

            }
            i++;
            j--;

        }

        int[] extremums = { 999999, -1 };
        for (int m = 0; m < positions.values().size(); m++) {
            Object[] values = positions.values().toArray();
            if ((int) values[m] < extremums[0]) {
                extremums[0] = (int) values[m];
            }
            if ((int) values[m] > extremums[1]) {
                extremums[1] = (int) values[m];
            }
        }
        // System.out.println(Arrays.toString(extremums));
        ArrayList<String> solution = new ArrayList<String>();
        int value1 = extremums[0];
        int value2 = extremums[1];

        for (int e : extremums) {
            for (Map.Entry k : positions.entrySet()) {
                if (e == (int) k.getValue()) {
                    solution.add(String.valueOf(k.getKey()));
                }
            }
        }

        System.out.println(String.join("", solution));
        return Integer.parseInt(String.join("", solution));

    }

    public static int part_2_better(String str) {
        return 1;
    }

    public static void main(String[] args) throws Exception {

        File file = new File("/home/rbr4t/AoC/2023/day1/task.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int sum = 0;
        while ((st = br.readLine()) != null) {
            sum += part_2(st);
        }
        System.out.println(sum);
    }
}
