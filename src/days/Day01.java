package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day01 {

    public static void main(String[] args) {

        ArrayList<Integer> input = new ArrayList<>();
        try {
            String path = "src/input/Day01_input.txt";
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
               input.add(Integer.parseInt(line));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Part 01
        boolean result = false;
        for (int i = 0; i < input.size()-1; i++) {
            for (int j=i+1; j < input.size(); j++) {
                int sum = input.get(i) + input.get(j);
                if (sum == 2020) {
                    System.out.println(input.get(i) + " " + input.get(j) + " " + sum + " " + (input.get(i) * input.get(j)));
                    result = true;
                    break;
                }
            }
            if (result) break;
        }

        //Part 02
        result = false;
        for (int i = 0; i < input.size()-2; i++) {
            for (int j=i + 1; j < input.size(); j++) {
                for (int k = j + 1; k < input.size(); k++) {
                    int sum = input.get(i) + input.get(j) + input.get(k);
                    if (sum == 2020) {
                        System.out.println(input.get(i) + " " + input.get(j) + " " + input.get(k) + " "
                                + sum + " " + (input.get(i) * input.get(j) * input.get(k)));
                        result = true;
                        break;
                    }
                }
                if (result) break;
            }
            if (result) break;
        }
    }



}
