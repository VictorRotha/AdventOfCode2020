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
    }

    public static ArrayList<Integer> readInput(String path) {
        ArrayList<Integer> timestamps = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            int timestamp = Integer.parseInt(br.readLine());
            for (String ts : br.readLine().split(",")) {
                if (!ts.equals("x")) {
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
            int waiting = bus - (timestamp % bus);
            if (waiting < minWaiting) {
                minWaiting = waiting;
                minID = bus;
            }
        }
        return minWaiting * minID;
    }
}
