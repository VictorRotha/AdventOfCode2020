package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day05 {

    public static void main(String[] args) {
        String path = "src/input/Day05_input.txt";
        ArrayList<Integer> input = readInput(path);

        int maxID = Collections.max(input);
        System.out.println("Max Seat ID = " + maxID);

        int minID = Collections.min(input);

        for (int i = minID + 1; i < maxID; i++) {
            if (!input.contains(i) && input.contains(i-1) && input.contains(i+1)) {
                System.out.println("My  Seat ID = " + i);
            }
        }
    }

    public static ArrayList<Integer> readInput(String path) {
        ArrayList<Integer> seatIDs = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            int row, col;
            while ((line=br.readLine()) != null) {
                row = findSeat(line.substring(0,7), 128);
                col = findSeat(line.substring(7,10).replace('R', 'B'), 8);
                seatIDs.add(row * 8 + col);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return seatIDs;
    }

    public static int findSeat(String group, int range) {
        int start = 0;
        for (int i = 0; i < group.length(); i++) {
            range /= 2;
            if (group.charAt(i) == 'B') {
                start = start + range;
            }
        }
        return start;
    }

}
