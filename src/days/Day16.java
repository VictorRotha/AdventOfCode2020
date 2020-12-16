package days;

import data.Day16Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day16 {

    public static void main(String[] args) {
        String path = "src/input/Day_16_input.txt";
        Day16Input input = readInput(path);
        System.out.printf("Part 01 Error sum is %s\n", invalidSum(input));
        System.out.printf("Part 02 product is %s\n", findFieldPos(input));
    }

    public static long findFieldPos(Day16Input input) {
        ArrayList<int[]> validTickets = skipInvalidTickets(input);
        HashMap<String, Integer> positions = new HashMap<>();
        HashMap<Integer, Set<String>> possibleNames = new HashMap<>();
        Set<String> fieldNames, invalidNames;
        boolean removed = true;

        while (removed) {
            removed = false;
            for (int idx = 0; idx < input.yourTicket.length; idx++) {

                fieldNames = new HashSet<>(input.fields.keySet());
                fieldNames.removeAll(positions.keySet());
                invalidNames = new HashSet<>();
                for (int[] ticket : validTickets) {
                    for (String fieldName : fieldNames) {
                        if (!invalidNames.contains(fieldName) && !inRange(input.fields.get(fieldName), ticket[idx])) {
                            invalidNames.add(fieldName);
                        }
                    }
                    fieldNames.removeAll(invalidNames);
                }
                possibleNames.put(idx, fieldNames);

                if (fieldNames.size() == 1) {
                    String fn = (String) fieldNames.toArray()[0];
                    positions.put(fn, idx);
                    for (int i : possibleNames.keySet()) {
                        possibleNames.get(i).remove(fn);
                        removed = true;
                    }
                }
            }
        }

        long prod =  1;
        for (String fieldName : positions.keySet() ) {
            if (fieldName.startsWith("departure")) {
                int pos = positions.get(fieldName);
                int value = input.yourTicket[pos];
                prod *= value;
            }
        }
        return prod;

    }

    public static boolean inRange(int[] range, int value) {
        return (value >= range[0] && value <= range[1]) || (value >= range[2] && value <= range[3]);
    }

    public static ArrayList<int[]> skipInvalidTickets(Day16Input input) {
        ArrayList<int[]> result =  new ArrayList<>();
        for (int[] ticket : input.tickets) {
            boolean valid = false;
            for (int value : ticket) {
                valid = false;
                for (int[] range : input.fields.values()) {
                    if (inRange(range, value)) {
                        valid = true;
                        break;
                    }
                }
                if (!valid) break;
            }
            if (valid) {
                result.add(ticket);
            }

        }
        return result;
    }

    public static int invalidSum(Day16Input input) {
        int result = 0;
        for (int[] ticket : input.tickets) {
            for (int value : ticket) {
                boolean valid = false;
                for (int[] range : input.fields.values()) {
                    if (inRange(range, value)) {
                       valid = true;
                       break;
                    }
                }
                if (!valid) {
                    result += value;
                }
            }
        }
        return result;
    }


    public static Day16Input readInput(String path) {
        Day16Input result = new Day16Input();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            int area = 0;

            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    area++;
                    continue;
                }
                if (area == 0) {
                    String fieldName = line.split(": ")[0];
                    int[] range = Arrays.stream(line.split(": ")[1].split("-| or ")).mapToInt(Integer::parseInt).toArray();
                    result.fields.put(fieldName, range);
                } else if (area == 1 && !line.equals("your ticket:")) {
                    result.yourTicket = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                } else if (area == 2 && !line.equals("nearby tickets:")) {
                    result.tickets.add(Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
