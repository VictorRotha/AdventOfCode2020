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

        int col = 0, trees = 0;
        int l = input.get(0).length();
        for (int row = 1; row < input.size(); row++) {
            col = (col + 3) % l;
            if (input.get(row).charAt(col) == '#') trees++;
        }
        System.out.println("Trees: " + trees);



    }

}
