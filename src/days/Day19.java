package days;

import data.Day19Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day19 {
    public static void main(String[] args) {
        String path = "src/input/Day19_input.txt";
        Day19Input input = readInput(path);

        String pattern = regex(0,input.rules);

        int valid = 0;
        for (String message : input.messages) {
            if (message.matches(pattern)) {
                valid++;
            }

        }
        System.out.printf("Part 01: %s valid messages\n", valid);

    }

    public static String regex(int rule, HashMap<Integer, String> rules) {
        String result = "";
        if (rules.get(rule).contains("\"")) {
            return rules.get(rule).substring(1,2);
        }

        if (rules.get(rule).contains("|")) {
            for (String subrule : rules.get(rule).split("\\|")) {
                int[] sub = Arrays.stream(subrule.strip().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int s : sub) {
                    result = result + regex(s, rules);
                }
                result = result + "|";

            }
            result = "(" + result.substring(0, result.length()-1) + ")";
        } else {
            int[] r = Arrays.stream(rules.get(rule).split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int ri : r) {
                result += regex(ri, rules);
            }
        }
        return result;
    }


    public static Day19Input readInput(String path) {
        HashMap<Integer, String> rules = new HashMap<>();
        ArrayList<String> messages = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            boolean m = false;
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    m = true;
                    continue;
                }
                if (!m) {
                    int key = Integer.parseInt(line.split(":")[0]);
                    String val = line.split(":")[1].strip();
                    rules.put(key, val);
                } else {
                    messages.add(line.strip());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Day19Input(rules, messages);
    }






}
