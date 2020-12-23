package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day23 {
    public static void main(String[] args) {
        String path = "src/input/Day23_input.txt";
        LinkedList<Integer> input = readInput(path);

        runGame(input, 100);

        System.out.println("final " + input);
        System.out.println("order " + order(input));

    }

    public static String order(LinkedList<Integer> input) {
        String result = "";
        int one = input.indexOf(1);
        Collections.rotate(input, -one);
        for (int i = 1; i < input.size(); i++) {
            result += input.get(i).toString();
        }
        return result;

    }

    public static void runGame(LinkedList<Integer> input, int rounds) {
        int currentCup;
        int[] pick = new int[3];
        for (int i = 0; i < rounds; i++) {

            currentCup = input.get(0);
            Collections.rotate(input, -1);
            pick[0] = input.pop();
            pick[1] = input.pop();
            pick[2] = input.pop();

            int destCup = destinationCup(currentCup, input);
            int destPos = input.indexOf(destCup);

            Collections.rotate(input, - destPos - 1);
            for (int p = 0; p < 3; p++) {
                input.add(pick[p]);
            }
            Collections.rotate(input, destPos + 4);

        }

    }

    public static int destinationCup(int currentCup, LinkedList<Integer> input) {
        int result = currentCup - 1;
        while (!input.contains(result)) {
            result = result - 1;
            if (result < Collections.min(input)) {
                result = Collections.max(input);
            }
        }
        return result;
    }


    public static LinkedList<Integer> readInput(String path) {
        LinkedList<Integer> result = new LinkedList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            for (int i = 0; i < line.length(); i++) {
                result.add(Integer.parseInt(line.charAt(i) + ""));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
