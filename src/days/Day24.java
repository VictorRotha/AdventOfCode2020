package days;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Day24 {

    public static void main(String[] args) {
        String path = "src/input/Day24_input.txt";
        ArrayList<ArrayList<String>> input = readInput(path);

        HashMap<String, Boolean> tiles = new HashMap<>();

        for (ArrayList<String> instructions : input) {
            int x = 0, y = 0;
            for (String instruction : instructions) {
                if (instruction.equals("e")) x+=2;
                if (instruction.equals("w")) x-=2;
                if (instruction.equals("ne")) {x++; y--;}
                if (instruction.equals("se")) {x++; y++;}
                if (instruction.equals("nw")) {x--; y--;}
                if (instruction.equals("sw")) {x--; y++;}
            }
            String key = x + "," + y;
            if (!tiles.containsKey(key)) {
                tiles.put(key, true);
            } else {
                if (tiles.get(key).equals(true)) {
                    tiles.put(key, false);
                } else {
                    tiles.put(key, true);
                }
            }
        }

        int sum = 0;
        for (boolean b : tiles.values()) {
            if (b) sum++;
        }
        System.out.println("Black Tiles: " + sum);
    }

    static  ArrayList<ArrayList<String>> readInput(String path) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                String instr = "";

                ArrayList<String> tile = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    instr += c;
                    if (c == 'e' || c == 'w') {
                        tile.add(instr);
                        instr = "";
                    }
                }
                result.add(tile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
