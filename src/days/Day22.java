package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Day22 {

    public static void main(String[] args) {
        String path = "src/input/Day22_input.txt";
        ArrayList<Integer> cards = readInput(path);

        LinkedList<Integer> deckOne = new LinkedList<>(cards.subList(0, cards.size() / 2));
        LinkedList<Integer> deckTwo = new LinkedList<>(cards.subList(cards.size() / 2, cards.size()));

        System.out.println("deck 1: " + deckOne);
        System.out.println("deck 2: " + deckTwo);

        LinkedList<Integer> deckWinner = runGame(deckOne, deckTwo);

        System.out.println("Winner: " + deckWinner);
        System.out.println("Score:  " + score(deckWinner));
    }

    public static int score(LinkedList<Integer> deckWinner) {
        int score = 0;
        int f = 1;
        for (int i = 0; i < deckWinner.size(); i++) {
            f = deckWinner.size() - i;
            score += deckWinner.get(i) * f;
        }
        return score;
    }

    public static LinkedList<Integer> runGame(LinkedList<Integer> deckOne, LinkedList<Integer> deckTwo) {
        while (deckOne.size() > 0 && deckTwo.size() >0) {
            int cardOne = deckOne.pop();
            int cardTwo = deckTwo.pop();
            if (cardOne > cardTwo) {
                deckOne.add(cardOne);
                deckOne.add(cardTwo);
            } else {
                deckTwo.add(cardTwo);
                deckTwo.add(cardOne);
            }
        }
        return (deckOne.size() == 0) ? deckTwo : deckOne;
    }

    public static ArrayList<Integer> readInput(String path) {
        ArrayList<Integer> deckOne = new ArrayList<>();
        ArrayList<Integer> deckTwo = new ArrayList<>();
        try {

            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            int player = 1;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Player")) continue;
                if (line.equals("")) {
                    player++;
                    continue;
                }
                if (player == 1) {
                    deckOne.add(Integer.parseInt(line.strip()));
                } else {
                    deckTwo.add(Integer.parseInt(line.strip()));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        deckOne.addAll(deckTwo);

        return deckOne;

    }
}
