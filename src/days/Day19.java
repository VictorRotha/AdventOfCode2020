package days;

import data.Day19Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day19 {
    public static void main(String[] args) {
        String path = "src/input/Day19_input.txt";
        Day19Input input = readInput(path);

        String pattern = regex(0,input.rules, 0, 20);
        int valid = 0;
        for (String message : input.messages) {
            if (message.matches(pattern)) valid++;
        }
        System.out.printf("Part 01: %s valid messages\n\n", valid);

        input.rules.put(8, "42 | 42 8");
        input.rules.put(11, "42 31 | 42 11 31");
        for (int maxD = 10; maxD <= 20; maxD++) {
            pattern = regex(0, input.rules, 0, maxD);
            valid = 0;
            for (String message : input.messages) {
                if (message.matches(pattern)) valid++;
            }
            System.out.printf("Part 02 max recursion depth = %s: %s valid messages\n", maxD, valid);
        }
    }

    public static String regex(int rule, HashMap<Integer, String> rules, int d, int maxD) {
        String result = "";
        if (d > maxD) return result;
        if (rules.get(rule).contains("\"")) return rules.get(rule).substring(1,2);
        for (String subrule : rules.get(rule).split("\\|")) {
            for (String s : subrule.strip().split(" ")) {
                result = result + regex(Integer.parseInt(s), rules, d+1, maxD);
            }
            result = result + "|";
        }
        result = "(" + result.substring(0, result.length()-1) + ")";
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
