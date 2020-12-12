package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Day12 {

    public static void main(String[] args) {
        String path = "src/input/Day12_input.txt";
        System.out.println("Part 01: The Manhattan Distance is " + readInput(path));
    }

    public static int readInput(String path) {
        char[] headings = {'E', 'S', 'W', 'N'};
        HashMap<Character, int[]> moving = new HashMap<>();
        moving.put('E', new int[]{+1, 0});
        moving.put('S', new int[]{0, -1});
        moving.put('W', new int[]{-1, 0});
        moving.put('N', new int[]{0, +1});

        int heading = 0;
        int x = 0, y = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {

                char dir = line.charAt(0);
                int value = Integer.parseInt(line.substring(1));

                if (dir =='F') {
                    dir = headings[heading];
                }
                if (moving.containsKey(dir)) {
                    x += moving.get(dir)[0] * value;
                    y += moving.get(dir)[1] * value;
                }

                if (dir == 'R') {
                    int newHead = (heading + value / 90);
                    heading = (newHead >= 0) ? newHead % 4 : (newHead % 4) + 4;
                }
                if (dir == 'L') {
                    int newHead = (heading - value / 90);
                    heading = (newHead >= 0) ? newHead % 4 : (newHead % 4) + 4;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Math.abs(x) + Math.abs(y);
    }
}
