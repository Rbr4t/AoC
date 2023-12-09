import java.io.*;
import java.util.*;

public class App {

    static ArrayList<ArrayList<Integer>> mapper(String row) {
        ArrayList<ArrayList<Integer>> levels = new ArrayList<>();

        // 1. turn that row into an ArrayList
        ArrayList<Integer> rowList = new ArrayList<>();
        for (String e : row.split(" ")) {
            rowList.add(Integer.parseInt(e));
        }
        levels.add(rowList);

        // 2. find the differences and construct a new ArrayList with that
        // & 3. while that isn't only 0-s, continue with task 2
        boolean flag = true;
        int index = 0;
        while (flag) {
            ArrayList<Integer> newRowList = new ArrayList<>();
            for (int i = 0; i < levels.get(index).size() - 1; i++) {
                newRowList.add(levels.get(index).get(i + 1) - levels.get(index).get(i));
            }
            levels.add(newRowList);
            index++;
            if (Collections.frequency(newRowList, newRowList.get(0)) == newRowList.size()) {
                flag = false;
            }
        }

        return levels;
    }

    static int part_1(String row) {
        ArrayList<ArrayList<Integer>> levels = mapper(row);
        // 4. when the list is only 0-s, traverse back to the initial list, by adding
        // last values of the lists together and returning the sum
        int lastN = 0;
        for (int i = levels.size() - 1; i >= 0; i--) {
            lastN += levels.get(i).get(levels.get(i).size() - 1);
        }

        return lastN;
    }

    static int part_2(String row) {

        ArrayList<ArrayList<Integer>> levels = mapper(row);

        // 4. when the list is only 0-s, traverse back from the front, by subtracting
        // from list before first element current list's first element
        int firstN = 0;
        for (int i = levels.size() - 1; i >= 0; i--) {
            firstN = levels.get(i).get(0) - firstN;
        }
        return firstN;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("/home/rbr4t/AoC/2023/day9/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        long sum1 = 0;
        long sum2 = 0;
        while ((st = br.readLine()) != null) {
            sum1 += part_1(st);
            sum2 += part_2(st);
        }

        System.out.println(sum1 + " " + sum2);

        br.close();
    }
}
