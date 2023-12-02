import java.io.*;

public class App {

    static int part_1(int id, String data) {
        String[] sliced_data = data.split(";");

        for (int i = 0; i < sliced_data.length; i++) {
            String[] entry = sliced_data[i].split(", ");
            int[] colours = { 0, 0, 0 }; // RGB

            for (String s : entry) {
                switch (s.trim().split(" ")[1]) {
                    case "red":
                        colours[0] += Integer.parseInt(s.trim().split(" ")[0]);
                        break;
                    case "green":
                        colours[1] += Integer.parseInt(s.trim().split(" ")[0]);
                        break;
                    case "blue":
                        colours[2] += Integer.parseInt(s.trim().split(" ")[0]);
                        break;
                    default:
                        break;
                }
            }

            // if any individual colour has more occurrences than the provided amounts, it
            // is automatically discarded
            if (colours[0] > 12 || colours[1] > 13 || colours[2] > 14) {
                return 0;
            }

        }
        return id;

    }

    static int part_2(String data) {
        String[] sliced_data = data.split(";");
        int[] minimums = { 0, 0, 0 }; // RGB

        for (int i = 0; i < sliced_data.length; i++) {
            String[] entry = sliced_data[i].split(", ");

            for (int j = 0; j < entry.length; j++) {
                int amount = Integer.parseInt(entry[j].trim().split(" ")[0]);

                switch (entry[j].trim().split(" ")[1]) {
                    case "red":
                        if (amount > minimums[0]) {
                            minimums[0] = amount;
                        }
                        break;
                    case "green":
                        if (amount > minimums[1]) {
                            minimums[1] = amount;
                        }
                        break;
                    case "blue":
                        if (amount > minimums[2]) {
                            minimums[2] = amount;
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        return minimums[0] * minimums[1] * minimums[2];
    }

    public static void main(String[] args) throws IOException {
        File file = new File("/home/rbr4t/AoC/2023/day2/data.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        int sum1 = 0;
        int sum2 = 0;

        while ((st = br.readLine()) != null) {
            int id = Integer.parseInt(st.split(": ")[0].split(" ")[1]);
            String data = st.split(": ")[1];
            sum1 += part_1(id, data);
            sum2 += part_2(data);
        }
        br.close();
        System.out.println(sum1);
        System.out.println(sum2);

    }
}
