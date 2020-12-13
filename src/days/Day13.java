package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day13 {

    public static void main(String[] args) {
        String path = "src/input/Day13_input.txt";
        ArrayList<Integer> input = readInput(path);
        int ts = input.get(input.size()-1);
        input.remove(input.size()-1);

        System.out.printf("Part 01 Solution is %s\n", nextBus(input, ts));
        System.out.printf("Part 02 Solution is %s\n", findTChinese(input));
    }

    public static ArrayList<Integer> readInput(String path) {
        ArrayList<Integer> timestamps = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            int timestamp = Integer.parseInt(br.readLine());
            for (String ts : br.readLine().split(",")) {
                if (ts.equals("x")) {
                    timestamps.add(0);
                } else {
                    timestamps.add(Integer.parseInt(ts));
                }
            }
            timestamps.add(timestamp);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return timestamps;
    }

    public static int nextBus(ArrayList<Integer> input, int timestamp) {
        int minWaiting = input.get(0);
        int minID = input.get(0);
        for (int bus : input) {
            if (bus == 0) continue;
            int waiting = bus - (timestamp % bus);
            if (waiting < minWaiting) {
                minWaiting = waiting;
                minID = bus;
            }
        }
        return minWaiting * minID;
    }

    //too slow !!
    public static long findT(ArrayList<Integer> input) {

        long t = input.get(0);
        long waiting;
        int bus;
        while (true) {
            boolean result = true;
            for (int i = 1; i < input.size(); i++) {
                bus = input.get(i);
                if (bus == 0) continue;
                waiting = bus - (t % bus);
                if (waiting != i){
                    result = false;
                    break;
                }
            }
            if (result) {
                return t;
            }
            t += input.get(0);
        }
    }

    //Chinese Remainder Theorem
    public static long findTChinese(ArrayList<Integer> input) {
        long pProd = 1;
        for (int i : input) {
            if (i != 0) pProd *= i;
        }

        long sum = 0, n;
        int p, j;
        for (int i = 1; i < input.size(); i++) {
            if ((p = input.get(i)) == 0) continue;
            n = (pProd / p);
            j = 1;
            while ((n * j) % p != 1) {
                j++;
            }
            sum += ((p - i) * n * j);
        }

        return sum % pProd;
    }

}
