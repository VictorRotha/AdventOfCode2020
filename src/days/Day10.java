package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day10 {

    public static void main(String[] args) {

        String path = "src/input/Day10_input.txt";
        ArrayList<Integer> input = readInput(path);

        System.out.printf("Part 01: product is %s\n", chainAdapters(input));
        System.out.printf("Part 02: %s possible adapter arrangements\n", findWaysFaster(input));
    }

    public static ArrayList<Integer> readInput(String path) {
     ArrayList<Integer> result = new ArrayList<>();
     try {
         BufferedReader br = new BufferedReader(new FileReader(path));
         String line;
         while ((line = br.readLine()) != null) {
             result.add(Integer.parseInt(line));
        }
     } catch (IOException e) {
         e.printStackTrace();
     }
     result.add(0);
     Collections.sort(result);
     result.add(result.get(result.size()-1) + 3);
     return result;
    }

    public static int chainAdapters(ArrayList<Integer> input) {
        int prevValue = 0;
        int dOne = 0, dThree = 0;
        for (Integer i : input) {
            if (i - prevValue == 1) {
                dOne++;
            } else if (i - prevValue == 3) {
                dThree++;
            }
            prevValue = i;
        }
        return dOne * (dThree);
    }

     // to slow !!
     public static long findWays(ArrayList<Integer> input, int start, int target) {
        long sum = 0;
        if (start == target) {
            return 1;
        }
        for (int d = start +1; d <= start + 3; d++) {
            if (input.contains(d)) {
                sum += findWays(input, d, target);
            }
        }
        return sum;
     }

     public static long findWaysFaster(ArrayList<Integer> input) {
         int[] rep = {0,1,2,4,7};
         int sumOnes = 0;
         long multSums = 1;
         for (int i = 0; i < input.size()-1; i++) {
             int diff = input.get(i+1) - input.get(i);
             if (diff == 3 && sumOnes != 0) {
                 multSums *= rep[sumOnes];
                 sumOnes = 0;
             } else if (diff == 1) {
                 sumOnes++;
             }

         }
         return multSums;
     }

}
