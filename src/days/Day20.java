package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day20 {

    public static void main(String[] args) {
        String path = "src/input/Day20_input.txt";
        HashMap<Integer, ArrayList<String>> input = readInput(path);

        HashMap<Integer, int[]> borders = new HashMap<>();
        for (int id : input.keySet()) borders.put(id, getborders(input.get(id)));

        ArrayList<Integer> allBorders = new ArrayList<>();
        for (int[] bs : borders.values()) {
            for (int border : bs) allBorders.add(border);
        }

        ArrayList<Integer> corners = findCorners(borders, allBorders);

        long prod = 1;
        for (int corner : corners) prod *= corner;

        System.out.printf("Part 01 product is %s\n", prod);

    }

    public static ArrayList<Integer> findCorners(HashMap<Integer, int[]> borders, ArrayList<Integer> allBorders) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int id: borders.keySet()) {
            int edges = 0;
            for (int b : borders.get(id)) {
                int counter = 0;
                for (int allB : allBorders) if (allB == b) counter++;
                if (counter == 1) edges++;
            }
            if (edges == 4) result.add(id);
        }
        return result;
    }

    public static String rightToLeft(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++ ) {
            result += s.charAt(s.length()-1-i);
        }
        return result;
    }

    public static int[] getborders(ArrayList<String> tile) {
        int[] borders = new int[8];
        String left = "", right = "";
        for (String line : tile) {
            left += line.charAt(0);
            right += line.charAt(line.length()-1);
        }
        borders[0] = Integer.parseInt(tile.get(0).replace('.', '0').replace('#', '1'), 2);
        borders[1] = Integer.parseInt(left.replace('.', '0').replace('#', '1'), 2);
        borders[2] = Integer.parseInt(right.replace('.', '0').replace('#', '1'), 2);
        borders[3] = Integer.parseInt(tile.get(tile.size()-1).replace('.', '0').replace('#', '1'), 2);

        String flipTop = rightToLeft(tile.get(0));
        String flipBottom = rightToLeft(tile.get(tile.size()-1));
        String flipLeft = rightToLeft(left);
        String flipRight = rightToLeft(right);

        borders[4] = Integer.parseInt(flipTop.replace('.', '0').replace('#', '1'), 2);
        borders[5] = Integer.parseInt(flipLeft.replace('.', '0').replace('#', '1'), 2);
        borders[6] = Integer.parseInt(flipRight.replace('.', '0').replace('#', '1'), 2);
        borders[7] = Integer.parseInt(flipBottom.replace('.', '0').replace('#', '1'), 2);

        return borders;
    }

    public static HashMap<Integer, ArrayList<String>> readInput(String path) {
        HashMap<Integer, ArrayList<String>> tiles = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            int id = 0;
            ArrayList<String> tile = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Tile")) {
                    String ids = line.split(" ")[1];
                    id = Integer.parseInt(ids.substring(0,ids.length()-1));
                } else if (line.equals("")) {
                    tiles.put(id, tile);
                    tile = new ArrayList<>();
                } else {
                    tile.add(line);
                }
             }
            if (tile.size() != 0) {
                tiles.put(id, tile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tiles;
    }

}
