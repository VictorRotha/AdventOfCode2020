package days;

import java.util.HashMap;

public class Day15 {

    public static void main(String[] args) {
        int [] input = {20,0,1,11,6,3};
        int maxTurns = 2020;
        System.out.printf("The %sth number is %s\n", maxTurns, runGame(input, maxTurns));
        maxTurns = 30000000;
        System.out.printf("The %sth number is %s\n", maxTurns, runGame(input, maxTurns));
    }

    public static int runGame(int [] input, int maxTurns) {
        HashMap<Integer, Integer> gameList = new HashMap<>();
        for (int i = 0; i < input.length-1 ; i++) {
            gameList.put(input[i], i);
        }
        int number = 0;
        int nextNumber = input[input.length-1];
        for (int turn = input.length-1; turn < maxTurns; turn++ ) {
            number = nextNumber;
            nextNumber = (!gameList.containsKey(number)) ? 0 : turn - gameList.get(number);
            gameList.put(number, turn);
        }
        return number;
    }

}
