package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day09 {

    public static void main(String[] args) {
        String path = "src/input/Day09_input.txt";
        ArrayList<Long> input = readInput(path);

        long inValidNumber = 0;
        int preamble = 25;
        for (int i = preamble; i < input.size(); i++) {
            if (!isValid(input, i, preamble)) {
                inValidNumber = input.get(i);
                break;
            }
        }
        System.out.printf("First invalid number is %s \n", inValidNumber);
        System.out.printf("The encryption weakness is %s \n", findSet(input, inValidNumber));
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

    public static Long findSet(ArrayList<Long> input, long inValidNumber) {
        long sum, minValue, maxValue;
        for (int i = 0; i < input.size(); i++) {
            sum = 0;
            int j = 0;
            if (input.get(i) < inValidNumber) {
                while (sum < inValidNumber) {
                    sum += input.get(i + j);
                    if (sum == inValidNumber) {
                        ArrayList<Long> result = new ArrayList<>();
                        for (int k = i; k <= i + j; k++) {
                            result.add(input.get(k));
                        }
                        minValue = Collections.min(result);
                        maxValue = Collections.max(result);
                        return minValue + maxValue;
                    }
                    j++;
                }
            }
        }

        return 0L;
    }

}
