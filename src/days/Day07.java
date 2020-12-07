package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Day07 {

    public static void main(String[] args) {
        String path = "src/input/Day07_input.txt";
        HashMap<String, ArrayList<String>> bags = readInput(path);
        String target = "shiny-gold";
        int count = 0;
        for (String bag :  bags.keySet()) {
            if (searchBags(bags, bag, target)) count++;
        }
        System.out.printf("%s bags can contain %s\n", count, target);
    }

    public static HashMap<String, ArrayList<String>> readInput(String path) {
        HashMap<String, ArrayList<String>> bags = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line, key;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(" ");
                key = String.join("-", Arrays.copyOfRange(s, 0, 2));
                if (!Arrays.asList(s).contains("other")) {
                    bags.put(key, new ArrayList<>());
                    for (int i = 4; i < s.length; i += 4) {
                        bags.get(key).add(String.join("-", Arrays.copyOfRange(s, i + 1, i + 3)));
                    }
                }
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bags;
    }

    public static boolean searchBags(HashMap<String, ArrayList<String>> bags, String bag, String target) {
        if (!bags.containsKey(bag)) {
            return false;
        } else {
            ArrayList<String> sbags = bags.get(bag);
            if (sbags.contains(target)) {
                return true;
            } else {
                for (String sbag : sbags) {
                    if (searchBags(bags, sbag, target)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
