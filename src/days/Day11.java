package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day11 {

    public static void main(String[] args) {
        String path = "src/input/Day11_input.txt";
        ArrayList<char[]> input = readInput(path);
        System.out.printf("Occupied Seats: %s\n", cycle(input));
    }

    public static ArrayList<char[]> readInput(String path) {
        ArrayList<char[]> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line.toCharArray());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void printSeats(ArrayList<char[]> input) {
        for (char[] row : input) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int cycle(ArrayList<char[]> input) {
        ArrayList<char[]> result;
        int w = input.get(0).length;
        boolean changed = true;
        int round = 0, occupied = 0;

        while (changed) {
            changed = false;
            round ++;
            occupied = 0;

            result = new ArrayList<>();
            for (int i = 0; i < input.size(); i++) {
                result.add(new char[w]);
            }

            for (int row = 0; row < input.size(); row++) {
                for (int col = 0; col < w; col++) {
                    char seat = input.get(row)[col];
                    int neighbours = findAdjacents(input, row, col);

                    if (seat == 'L' && neighbours == 0) {
                        result.get(row)[col] = '#';
                        changed = true;
                    } else if (seat == '#' && neighbours >= 4) {
                        result.get(row)[col] = 'L';
                        changed = true;
                    } else {
                        result.get(row)[col] = seat;
                    }

                    if (result.get(row)[col] == '#') {
                        occupied++;
                    }
                }
            }
            input = result;
        }
        return occupied;
    }

    public static int findAdjacents(ArrayList<char[]> input, int row, int col) {
        int neighbours = 0;
        for (int c = col -1; c <= col +1; c++) {
            if (!(c < 0 || c >= input.get(0).length)) {
                for (int r = row - 1; r <= row + 1; r++) {
                    if (!(r < 0 || r >= input.size() || (r == row && c == col))) {
                        char seat = input.get(r)[c];
                        if (seat == '#') {
                            neighbours++;
                        }
                    }
                }
            }
        }
        return neighbours;
    }


}