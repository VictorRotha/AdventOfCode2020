package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day03 {

    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<>();
        try {
            String path = "src/input/Day03_input.txt";
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        int product = slope(1,1,input) *
                slope(3,1,input) *
                slope(5,1,input) *
                slope(7,1,input) *
                slope(1,2,input);

        System.out.println("Part 01: " + slope(3,1,input));
        System.out.println("Part 02: " + product);
    }

    public static int slope(int right, int down, ArrayList<String> input) {
        int col = 0, trees = 0;
        int l = input.get(0).length();
        for (int row = down; row < input.size(); row+=down) {
            col = (col + right) % l;
            if (input.get(row).charAt(col) == '#') trees++;
        }
        return trees;
    }

}
