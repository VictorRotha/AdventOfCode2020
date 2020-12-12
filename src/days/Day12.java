package days;

import data.Day12Instruction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day12 {

    public static void main(String[] args) {
        String path = "src/input/Day12_input.txt";
        ArrayList<Day12Instruction> input = readInput(path);

        System.out.println("Part 01: The Manhattan Distance is " + navigate(input));
        System.out.println("Part 02: The Manhattan Distance is " + waypointNavigate(input));
    }

    public static ArrayList<Day12Instruction> readInput(String path) {
        ArrayList<Day12Instruction> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                char dir = line.charAt(0);
                int value = Integer.parseInt(line.substring(1));
                result.add(new Day12Instruction(dir, value));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int waypointNavigate(ArrayList<Day12Instruction> input) {
        HashMap<Character, int[]> moving = new HashMap<>();
        moving.put('E', new int[]{+1, 0});
        moving.put('S', new int[]{0, -1});
        moving.put('W', new int[]{-1, 0});
        moving.put('N', new int[]{0, +1});

        int[] waypoint = {10, 1};
        int[] pos = {0,0};

        for (Day12Instruction instr : input) {
            char dir = instr.dir;
            int value = instr.value;

            if (moving.containsKey(dir)) {
                waypoint[0] += moving.get(dir)[0] * value;
                waypoint[1] += moving.get(dir)[1] * value;
            }

            if (dir == 'R' || dir == 'L') {
                int turn = (value / 90) % 4;
                if (dir == 'L') {
                    turn = (4 - turn);
                }
                int[] temp = new int[2];
                switch (turn) {
                    case 1:
                        temp[0] = waypoint[1];
                        temp[1] = -waypoint[0];
                        waypoint = temp;
                        break;
                    case 2:
                        waypoint[0] = -waypoint[0];
                        waypoint[1] = -waypoint[1];
                        break;
                    case 3:
                        temp[0] = -waypoint[1];
                        temp[1] = waypoint[0];
                        waypoint = temp;
                        break;
                }
            }
            if (dir == 'F') {
                pos[0] += waypoint[0] * value;
                pos[1] += waypoint[1] * value;
            }

        }
        return Math.abs(pos[0]) + Math.abs(pos[1]);
    }

    public static int navigate(ArrayList<Day12Instruction> input) {
        char[] headings = {'E', 'S', 'W', 'N'};
        HashMap<Character, int[]> moving = new HashMap<>();
        moving.put('E', new int[]{+1, 0});
        moving.put('S', new int[]{0, -1});
        moving.put('W', new int[]{-1, 0});
        moving.put('N', new int[]{0, +1});

        int heading = 0;
        int[] pos = {0, 0};

        for (Day12Instruction instr : input) {
            char dir = instr.dir;
            int value = instr.value;

            if (dir == 'F') {
                dir = headings[heading];
            }
            if (moving.containsKey(dir)) {
                pos[0] += moving.get(dir)[0] * value;
                pos[1] += moving.get(dir)[1] * value;
            }

            if (dir == 'R' || dir == 'L') {
                int turn = (value / 90) % 4;
                if (dir == 'L') {
                    turn = 4 - turn;
                }
                heading  = (heading + turn) % 4;
            }
        }
        return Math.abs(pos[0]) + Math.abs(pos[1]);
    }

}
