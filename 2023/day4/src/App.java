import java.io.*;
import java.util.*;

public class App {

    static double part_1(ArrayList winNumbers, ArrayList regNumbers) {
        double counter = 0;
        for (Object N : winNumbers) {
            N = (int) N;
            if (regNumbers.contains(N)) {
                counter++;
            }
        }
        if (counter > 0) {
            return Math.pow(2.0, counter - 1);
        }
        return 0;
    }

    static Object[] part_2(ArrayList winNumbers, ArrayList regNumbers, int currentGame) {
        int counter = 0;
        // check how many winning checkcards are there
        for (Object N : winNumbers) {
            N = (int) N;
            if (regNumbers.contains(N)) {
                counter++;
            }
        }

        ArrayList<Integer> new_tickets = new ArrayList<>();

        // make a list of those Game ID's
        for (int i = 1; i < counter + 1; i++) {
            new_tickets.add(currentGame + i);
        }

        return new_tickets.toArray();
    }

    public static void main(String[] args) throws Exception {
        File file = new File("/home/rbr4t/AoC/2023/day4/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int sum = 0;

        ArrayList<Integer> tickets = new ArrayList<Integer>();
        int amount = 0;

        while ((st = br.readLine()) != null) {
            int game_amount = 0;
            ArrayList<Integer> winNumbers = new ArrayList<Integer>();
            ArrayList<Integer> regNumbers = new ArrayList<Integer>();

            for (String element : st.split("\\|")[0].trim().replaceAll(" +", " ").split(":")[1].trim().split(" ")) {
                winNumbers.add(Integer.parseInt(element));
            }
            for (String element : st.split("\\|")[1].trim().replaceAll(" +", " ").split(" ")) {
                regNumbers.add(Integer.parseInt(element));
            }

            // part 1 solution
            sum += part_1(winNumbers, regNumbers);

            // part 2 solution
            // first get the matching GAME ID, process how many tickets that would make
            int gameId = Integer.parseInt(st.split("\\|")[0].split(": ")[0].trim().replaceAll(" +", " ").split(" ")[1]);

            Object[] res = part_2(winNumbers, regNumbers, gameId);

            // current ticket
            game_amount++;

            for (Object object : res) {
                tickets.add((int) object);
            }

            // then, get all the other tickets that the function returned,
            // add them to an array

            Collections.sort(tickets);

            // check all the other tickets with the same game numbers as ours
            // find out how many of these are there and add it to the total
            // and process our current tickets and add its leading tickets to the array
            int amount_in_array = Collections.frequency(tickets, gameId);
            game_amount += amount_in_array;

            for (int i = 0; i < amount_in_array; i++) {

                for (Object object : res) {
                    tickets.add((int) object);
                }

            }

            // remove the previously collected occurrences
            tickets.removeAll(new ArrayList<>(gameId));

            amount += game_amount;

        }
        System.out.println(sum + " " + amount);

        br.close();
    }
}
