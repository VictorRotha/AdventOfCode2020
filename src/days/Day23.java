package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day23 {
    public static void main(String[] args) {
        String path = "src/input/Day23_input.txt";


        //Part01
        LinkedList<Integer> input = readInput(path);

        runGame(input, 100);
        System.out.println("Part 01 label: " + order(input));

        //Part02

        ArrayList<Integer> bigInput = new ArrayList<>(readInput(path));

        int maxCups = 1_000_000;
        int rounds = 10_000_000;

        HashMap<Integer, Integer> cups = increaseCupNumbers(bigInput, maxCups);
        runGame2(cups, bigInput.get(0), maxCups, rounds);

        int firstStar = cups.get(1);
        int secondStar = cups.get(firstStar);
        long result = (long) firstStar * (long) secondStar;

        System.out.printf("Part 02 star product: %s (%s * %s)\n", result, firstStar, secondStar);

    }

    public static HashMap<Integer, Integer> increaseCupNumbers(ArrayList<Integer> input, int maxCups) {
        HashMap<Integer, Integer> cups = new HashMap<>();
        for (int i = 0; i < input.size()-1; i++) {
            cups.put(input.get(i), input.get(i+1));
        }
        cups.put(input.get(input.size()-1), Collections.max(input) + 1);
        for (int i = input.size() + 1; i <= maxCups; i++) {
            cups.put(i, i+1);
            if (i == maxCups) {
                cups.put(i, input.get(0));
            }
        }
        return cups;
    }

    public static void runGame2(HashMap<Integer, Integer> cups, int start, int maxCup, int rounds) {
        int currCup = start;
        Integer[] pick = new Integer[3];
        for (int i = 0; i < rounds; i++) {

            pick[0] = cups.get(currCup);
            pick[1] = cups.get(pick[0]);
            pick[2] = cups.get(pick[1]);

            int destCup = destinationCup2(currCup, pick, maxCup);
            move(cups, currCup, destCup, pick);
            currCup = cups.get(currCup);

//            if (i % (rounds / 100) == 0) {
//                System.out.println(100 * i / rounds + " % " + i + " rounds");
//            }
        }
    }

    public static void move(HashMap<Integer, Integer> cups, int currCup, int destCup, Integer[] pick) {
        cups.put(currCup, cups.get(pick[2]));
        cups.put(pick[2], cups.get(destCup));
        cups.put(destCup, pick[0]);
    }

    public static int destinationCup2(int currentCup, Integer[] pick, int maxCup) {
        int result = (currentCup != 1) ? currentCup - 1 : maxCup;
        while (Arrays.asList(pick).contains(result)) {
            result = (result != 1) ? result - 1 : maxCup;
        }
        return result;
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

//            if (i % (rounds/100) == 0) {
//                System.out.println(100 * i / rounds + " % " + i);
//            }
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


 static LinkedList<Integer> readInput(String path) {
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
