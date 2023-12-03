import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    static ArrayList<HashMap> parser(String row, int level) {
        ArrayList<HashMap> numbers = new ArrayList<>();
        StringBuilder constructed_number = new StringBuilder("");
        HashMap<String, Integer> element = new HashMap<>();

        int starting_index = -1;
        for (int i = 0; i < row.length(); i++) {
            if ("0123456789".indexOf(row.charAt(i)) > -1) {
                if (starting_index == -1) {
                    starting_index = i;
                }
                constructed_number.append(row.charAt(i));
            } else {
                if (constructed_number.length() > 0) {
                    element.put("substring", Integer.parseInt(constructed_number.toString()));
                    element.put("index", starting_index);
                    element.put("length", constructed_number.length());
                    element.put("level", level);
                    numbers.add(element);
                    element = new HashMap<>();
                    starting_index = -1;
                    constructed_number.delete(0, constructed_number.length());
                }
            }

            if (i == row.length() - 1 && constructed_number.length() > 0) {
                element.put("substring", Integer.parseInt(constructed_number.toString()));
                element.put("index", starting_index);
                element.put("length", constructed_number.length());
                element.put("level", level);
                numbers.add(element);
                element = new HashMap<>();
                starting_index = -1;
                constructed_number.delete(0, constructed_number.length());
            }
            row = "x".repeat(i) + row.substring(i, row.length());
        }

        return numbers;
    }

    static boolean check_surroundings(String[] levels, int number_length, int index) {
        for (int i = 0; i < levels.length; i++) {
            String level = levels[i];
            String surroundings = "";

            for (int j = -1; j < number_length + 1; j++) {
                int real_index = index + j;
                if (real_index >= 0 && real_index < level.length()) {

                    if (!("0123456789".indexOf(level.charAt(real_index)) > -1)) {
                        surroundings += level.charAt(index + j);
                    }
                }
            }
            Pattern pattern = Pattern.compile("[^.]+");
            Matcher matcher = pattern.matcher(surroundings);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }

    static int part_1(ArrayList<String> structure) {
        // first, we will identify and match all the numbers
        // second we will get their starting index and length and combine them all to
        // one hashmap

        int sum = 0;
        ArrayList<HashMap> parsed_data = new ArrayList<HashMap>();

        for (int i = 0; i < structure.size(); i++) {
            for (HashMap e : parser(structure.get(i), i)) {
                parsed_data.add(e);
            }
        }

        // third, we will we will use the index and the length, to check the surrounding
        // perimeter of that number for special characters, and we will return the
        // number if that's the case

        for (HashMap element : parsed_data) {
            boolean ok = false;

            if ((int) element.get("level") == 0) {

                String[] levels = { "", "" };
                levels[0] = structure.get(0);
                levels[1] = structure.get(1);

                ok = check_surroundings(levels, (int) element.get("length"), (int) element.get("index"));

            } else if ((int) element.get("level") == 139) {
                String[] levels = { "", "" };

                levels[0] = structure.get((int) element.get("level") - 1);
                levels[1] = structure.get((int) element.get("level"));

                ok = check_surroundings(levels, (int) element.get("length"), (int) element.get("index"));

            } else {
                String[] levels = { "", "", "" };
                levels[0] = structure.get((int) element.get("level") - 1);
                levels[1] = structure.get((int) element.get("level"));
                levels[2] = structure.get((int) element.get("level") + 1);

                ok = check_surroundings(levels, (int) element.get("length"), (int) element.get("index"));

            }

            // fifth, if there is no special character in its vicinity, then add to the sum
            if (ok) {
                sum += (int) element.get("substring");
            }

        }
        return sum;
    }

    static ArrayList<Integer> gear_finder(String row) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < row.length(); i++) {
            if (row.charAt(i) == '*') {
                result.add(i);
            }
            row = "x".repeat(i) + row.substring(i, row.length());
        }

        return result;
    }

    static int check_gear(String[] levels, int index) {
        int counter = 0;
        ArrayList<Integer> results = new ArrayList<Integer>();

        for (int i = 0; i < levels.length; i++) {
            // System.out.println();
            String level = levels[i];

            // loop for checking adjacent tiles
            for (int j = -1; j < 2; j++) {
                int real_index = index + j;

                // if it's not out of the borders
                if (real_index >= 0 && real_index < level.length()) {

                    // and the char is numeric
                    if ("0123456789".indexOf(level.charAt(real_index)) > -1) {
                        int end = level.length();
                        int start = 0;

                        // we will try to extract the numeral
                        for (int k = real_index; k < level.length(); k++) {

                            if ("0123456789".indexOf(level.charAt(k)) == -1) {
                                end = k;
                                break;
                            }
                        }

                        for (int m = (end - 1); m >= 0; m--) {
                            if ("0123456789".indexOf(level.charAt(m)) == -1) {
                                start = m + 1;
                                break;
                            }
                        }

                        results.add(Integer.parseInt(level.substring(start, end)));
                        level = level.substring(0, start)
                                + "x".repeat(level.substring(start, end).toString().length())
                                + level.substring(end, level.length());

                        counter++;
                    }
                }
            }
        }

        if (counter == 2) {
            return results.get(0) * results.get(1);
        }
        return 0;
    }

    static int part_2(ArrayList<String> structure) {
        int sum = 0;
        for (int i = 0; i < structure.size(); i++) {

            for (Integer e : gear_finder(structure.get(i))) {

                if (i == 0) {
                    String[] levels = { "", "" };
                    levels[0] = structure.get(i);
                    levels[1] = structure.get(i + 1);

                    sum += check_gear(levels, e);
                } else if (i == (structure.size() - 1)) {
                    String[] levels = { "", "" };
                    levels[0] = structure.get(i - 1);
                    levels[1] = structure.get(i);

                    sum += check_gear(levels, e);

                } else {
                    String[] levels = { "", "", "" };
                    levels[0] = structure.get(i - 1);
                    levels[1] = structure.get(i);
                    levels[2] = structure.get(i + 1);

                    sum += check_gear(levels, e);

                }
            }
        }
        return sum;

    }

    public static void main(String[] args) throws Exception {
        File file = new File("/home/rbr4t/AoC/2023/day3/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        ArrayList<String> structure = new ArrayList<String>();

        while ((st = br.readLine()) != null) {
            structure.add(st);
        }
        System.out.println(part_1(structure) + " " + part_2(structure));

        br.close();
    }
}
