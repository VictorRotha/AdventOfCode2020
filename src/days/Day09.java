package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day09 {

    public static void main(String[] args) {
        String path = "src/input/Day09_input.txt";
        ArrayList<Long> input = readInput(path);

        int preamble = 25;
        for (int i = preamble; i < input.size(); i++) {
            if (!isValid(input, i, preamble)) {
                System.out.printf("First invalid number is %s as index %s\n", input.get(i), i);
                break;
            }
        }

    }

    public static ArrayList<Long> readInput(String path) {
        ArrayList<Long> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                result.add(Long.parseLong(line.strip()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isValid(ArrayList<Long> input, int index, int preamble) {
        for (int j = 0; j < preamble; j++) {
            if (input.subList(index - preamble, index).contains((input.get(index) - input.subList(index - preamble, index).get(j)))) {
                return true;
            }
        }
        return false;
    }
}
