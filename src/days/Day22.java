package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Day22 {

    public static void main(String[] args) {
        String path = "src/input/Day22_input.txt";
        LinkedList<Integer>[] decks = readInput(path);

        LinkedList<Integer> deckWinner = runCombat(new LinkedList<>(decks[0]), new LinkedList<>(decks[1]));
        System.out.println("Part 01 Score:  " + score(deckWinner));

        LinkedList<Integer>[] decksR =  runRecursiveCombat(decks, new ArrayList<>());
        LinkedList<Integer> deckWinnerRecursive = (decksR[0].size() == 0) ? decksR[1] : decksR[0];
        System.out.println("Part 02 Score:  " + score(deckWinnerRecursive));
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

    public static LinkedList<Integer>[] runRecursiveCombat(LinkedList<Integer>[] decks, ArrayList<String> states) {
        while (decks[0].size() > 0 && decks[1].size() >0) {
            String state = decks[0].toString() + decks[1].toString();
            if (states.contains(state)) {
                return decks;
            } else {
                states.add(state);
            }
            int cardOne = decks[0].pop();
            int cardTwo = decks[1].pop();

            if (cardOne <= decks[0].size() && cardTwo <= decks[1].size()) {
                LinkedList<Integer>[] subDecks = new LinkedList[2];
                subDecks[0] = new LinkedList<>(decks[0].subList(0, cardOne));
                subDecks[1] = new LinkedList<>(decks[1].subList(0, cardTwo));
                LinkedList<Integer>[] subGame = runRecursiveCombat(subDecks, new ArrayList<>());
                if (subGame[0].size() == 0) {
                    decks[1].add(cardTwo);
                    decks[1].add(cardOne);
                } else {
                    decks[0].add(cardOne);
                    decks[0].add(cardTwo);
                }

            } else {
                if (cardOne > cardTwo) {
                    decks[0].add(cardOne);
                    decks[0].add(cardTwo);
                } else {
                    decks[1].add(cardTwo);
                    decks[1].add(cardOne);
                }
            }
        }
        return decks;
    }

    public static LinkedList<Integer> runCombat(LinkedList<Integer> deckOne, LinkedList<Integer> deckTwo) {
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

    public static LinkedList<Integer>[] readInput(String path) {

        LinkedList<Integer>[] game = new LinkedList[2];
        game[0] = new LinkedList<>();
        game[1] = new LinkedList<>();
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
                    game[0].add(Integer.parseInt(line.strip()));
                } else {
                    game[1].add(Integer.parseInt(line.strip()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return game;
    }
}
