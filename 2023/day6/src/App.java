import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("/home/rbr4t/AoC/2023/day6/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        Map<Long, ArrayList<Long>> board = new HashMap();

        long prod = 1;
        String[] time = br.readLine().split(":")[1].trim().split("\\s+");
        String[] distances = br.readLine().split(":")[1].trim().split("\\s+");

        for (int i = 0; i < time.length; i++) {
            if (board.keySet().contains(Long.parseLong(time[i]))) {
                ArrayList<Long> entry = board.get(Long.parseLong(time[i]));
                entry.add(Long.parseLong(distances[i]));
                board.put(Long.parseLong(time[i]), entry);

            } else {
                ArrayList<Long> newEntry = new ArrayList<>();
                newEntry.add(Long.parseLong(distances[i]));
                board.put(Long.parseLong(time[i]), newEntry);

            }
        }

        // part 1
        /*
         * Time: 7 15 30
         * Distance: 9 40 200
         * 
         */

        for (Long key : board.keySet()) {

            for (Long e : board.get(key)) {
                long waysToWin = 0;

                for (int i = 1; i <= key; i++) {
                    if (((key - i) * i) > e) {
                        waysToWin++;
                    }
                }
                prod *= waysToWin;

            }
        }

        // for part 2 I removed the spaces manually from the input file and ran the same
        // program
        /*
         * Time: 71530
         * Distance: 940200
         * 
         */
        System.out.println(prod);

    }
}
