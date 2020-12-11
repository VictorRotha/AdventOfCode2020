package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day11 {

    public static void main(String[] args) {
        String path = "src/input/Day11_input.txt";
        int w = seatsWidth(path);
        ArrayList<Character> input = readInput(path);

        System.out.printf("Part 01: %s Occupied Seats\n", cycle(input, w, false));
        System.out.printf("Part 02: %s Occupied Seats\n", cycle(input, w, true));
    }

    public static int seatsWidth(String path) {
        int result = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            result = br.readLine().length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Character> readInput(String path) {
        ArrayList<Character> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    result.add(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void printSeats(ArrayList<Character> input, int width) {
        for (int i = 0; i < input.size(); i++) {
            System.out.print(input.get(i) + " ");
            if ((i+1) % width == 0) {
                System.out.println();
            }
        }
    }

    public static boolean inSeats(ArrayList<Character> input, int row, int col, int width) {
        return (col >= 0 && col < width && row >= 0 && row < input.size() / width);
    }

    public static int cycle(ArrayList<Character> input, int width, boolean vis) {
        ArrayList<Character> result;
        boolean changed = true;
        int occupied = 0;
        int neighbours;
        char seat;
        int maxNeighbours = (vis) ? 5 : 4;

        while (changed) {
            changed = false;
            occupied = 0;
            result = new ArrayList<>();
            for (int i = 0; i < input.size(); i++) {
                seat = input.get(i);
                neighbours = findAdjacents(input, i, width, vis);

                if (seat == 'L' && neighbours == 0) {
                    result.add('#');
                    changed = true;
                } else if (seat == '#' && neighbours >= maxNeighbours) {
                    result.add('L');
                    changed = true;
                } else {
                    result.add(seat);
                }

                if (result.get(i) == '#') {
                    occupied++;
                }
            }
            input = result;
        }
        return occupied;
    }

    public static int findAdjacents(ArrayList<Character> input, int index, int width, boolean vis) {
        int[][] nextPositions = {{+1, +1}, {-1, +1}, {-1, -1}, {+1, -1}, {0, -1}, {0, +1}, {-1, 0}, {+1, 0}};
        int neighbours = 0;
        int row = index / width;
        int col = index % width;
        int nextRow, nextCol;
        char nextSeat;

        for (int[] nextPos : nextPositions) {
            nextRow = row + nextPos[0];
            nextCol = col + nextPos[1];
            if (vis) {
                while (inSeats(input, nextRow, nextCol, width)) {
                    nextSeat = input.get(nextRow * width + nextCol);
                    if (nextSeat == '#') neighbours++;
                    if (nextSeat != '.') break;
                    nextRow = nextRow + nextPos[0];
                    nextCol = nextCol + nextPos[1];
                }
            } else {
                if (inSeats(input, nextRow, nextCol, width)) {
                    nextSeat = input.get(nextRow * width + nextCol);
                    if (nextSeat == '#') neighbours++;
                }
            }
         }
        return neighbours;
    }

}
