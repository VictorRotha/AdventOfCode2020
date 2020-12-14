package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Day14 {
    public static void main(String[] args) {

        String path = "src/input/Day14_input.txt";
        System.out.printf("Part 01 sum is %s\n", readInput(path));

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

}
