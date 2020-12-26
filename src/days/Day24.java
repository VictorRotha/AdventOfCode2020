package days;


import data.Day24Tile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Day24 {

    public static void main(String[] args) {
        String path = "src/input/Day24_input.txt";
        ArrayList<ArrayList<String>> input = readInput(path);
        HashMap<String, Day24Tile> tiles = startTiles(input);
        System.out.println("Part 01 black tiles: " + blackSum(tiles));

        System.out.println("start tiles: ");
        printTiles(tiles);

        long now = System.currentTimeMillis();
        for (int i = 1; i <= 100; i++) {
            runDay(tiles);
//            System.out.println("blacks tiles after day " + i + ": " + blackSum(tiles));
        }
        System.out.println(System.currentTimeMillis() - now + " ms");
        System.out.println("Part 02 blacks tiles after day 100: " + blackSum(tiles));


    }

    public static void printTiles(HashMap<String, Day24Tile> tiles) {
        int[] borders = borders(tiles);
        for (int y = borders[2]; y <= borders[3]; y++) {

            String row = "";
            for (int x = borders[0]; x <= borders[1]; x++) {
                if (!isValid(x, y)) {
                    row += " ";

                } else {
                    Day24Tile tile = new Day24Tile(x, y);
                    if (tiles.containsKey(tile.toString())) {
                        if (tiles.get(tile.toString()).black) {
                            row += "#";
                        } else {
                            row += "o";
                        }
                    } else {
                        row += ".";
                    }
                }
            }
            System.out.println(row);
        }

    }

    public static void runDay(HashMap<String, Day24Tile> tiles) {
        int[] borders = borders(tiles);
        for (int x = borders[0] - 2; x <= borders[1] + 2; x++) {
            for (int y = borders[2] - 2; y <= borders[3] + 2; y++) {
                if (!isValid(x, y)) continue;
                Day24Tile tile = new Day24Tile(x, y);
                String key = tile.toString();
                if (tiles.containsKey(key)) {
                    tiles.get(key).nbs = neighbours(tiles, tiles.get(key));
                } else {
                    tile.nbs = neighbours(tiles, tile);
                    if (tile.nbs == 2) {
                        tile.black = false;
                        tiles.put(key, tile);
                    }
                }
            }
        }

        for (Day24Tile tile : tiles.values()) {
             if (tile.black && (tile.nbs == 0 || tile.nbs > 2)) {
                tile.flip();
            } else if (!tile.black && tile.nbs == 2) {
                tile.flip();
            }
        }
    }

    public static  int neighbours(HashMap<String, Day24Tile> tiles, Day24Tile tile) {
        Day24Tile[] neighbours = new Day24Tile[] {
                new Day24Tile(tile.x-2, tile.y),
                new Day24Tile(tile.x+2, tile.y),
                new Day24Tile(tile.x-1, tile.y-1),
                new Day24Tile(tile.x-1, tile.y+1),
                new Day24Tile(tile.x+1, tile.y-1),
                new Day24Tile(tile.x+1, tile.y+1)
        };
        int result = 0;
        for (Day24Tile nb : neighbours) {
            if (!tiles.containsKey(nb.toString())) continue;
            if (tiles.get(nb.toString()).black) result++;
        }
        return result;
    }

    public static int[] borders(HashMap<String, Day24Tile> tiles) {
        //left, right, top, bottom
        int[] result = new int[] {0, 0, 0, 0};
        for (Day24Tile t : tiles.values()) {
            if (!t.black) continue;
            result[0] = Math.min(result[0], t.x);
            result[1] = Math.max(result[1], t.x);
            result[2] = Math.min(result[2], t.y);
            result[3] = Math.max(result[3], t.y);
        }
        if (!isValid(result[0], result[2])) {
            result[2]--;
        }
        if (!isValid(result[1], result[3])) {
            result[3]++;
        }

        return result;
    }

    public static boolean isValid(int x, int y) {
        return ((x % 2 == 0 && y % 2 == 0) || (x % 2 != 0 && y % 2 != 0));
    }

    public static int blackSum(HashMap<String, Day24Tile> tiles) {
        int sum = 0;
        for (Day24Tile t : tiles.values()) {
            if (t.black) sum++;
        }
        return sum;
    }

    public static HashMap<String, Day24Tile> startTiles(ArrayList<ArrayList<String>> input) {
        HashMap<String, Day24Tile> tiles = new HashMap<>();
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
            Day24Tile tile = new Day24Tile(x, y);
            String key = tile.toString();
            if (tiles.containsKey(key)) {
                tiles.get(key).flip();
            } else {
                tiles.put(key, tile);
            }
        }
        return tiles;
    }


    public static ArrayList<ArrayList<String>> readInput(String path) {
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
