package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day14 {
    public static void main(String[] args) {

        String path = "src/input/Day14_input.txt";
        System.out.printf("Part 01 sum is %s\n", readInput(path));
        System.out.printf("Part 02 sum is %s\n", readInputV2(path));
    }

    public static long readInput(String path) {
        String mask;
        HashMap<Long, Long> mem = new HashMap<>();
        long m1 = 0, m2 = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("mask")) {
                    mask = line.split("=")[1].strip();
                    m1 = Long.parseLong(mask.replace('X', '0'), 2);
                    m2 = Long.parseLong(mask.replace('X', '1'), 2);
                } else {
                    long idx = Long.parseLong(line.split("[\\[\\]]")[1]);
                    long value = Long.parseLong(line.split(" ")[2]);
                    value = value | m1;
                    value = value & m2;
                    mem.put(idx, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long sum = 0;
        for (long k : mem.values()) {
            sum += k;
        }
        return sum;
    }


    public static long readInputV2(String path) {

        String mask = "";
        HashMap<Long, Long> mem = new HashMap<>();
        ArrayList<Integer> posX = new ArrayList<>();
        long address, value;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("mask")) {
                    mask = line.split("=")[1].strip();
                    posX.clear();
                    for (int i = 0; i < mask.length(); i++) {
                        if (mask.charAt(i) == 'X') {
                            posX.add(mask.length() - i - 1);
                        }
                    }
                    mask = mask.replace('X', '0');
                } else {
                    address = Long.parseLong(line.split("[\\[\\]]")[1]);
                    address = address | Long.parseLong(mask, 2);
                    value = Long.parseLong(line.split(" ")[2]);
                    ArrayList<Long> adresses = combinations(address, posX, 0);
                    for (long a : adresses) {
                        mem.put(a, value);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        long sum = 0;
        for (long v : mem.values()) {
            sum += v;
        }
        return sum;
    }

    public static ArrayList<Long> combinations(long address, ArrayList<Integer> posX, int idx) {
        ArrayList<Long> result = new ArrayList<>();
        if (idx == posX.size()) {
            result.add(address);
            return result;
        }
        long m1 = (long) Math.pow(2, posX.get(idx));
        long m2 = ~ m1;
        result.addAll(combinations(address | m1, posX, idx+1));
        result.addAll(combinations(address & m2, posX, idx+1));
        return result;
    }

}
