package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;


public class Day07 {

    public static void main(String[] args) {
        String path = "src/input/Day07_input.txt";
        HashMap<String, HashMap<String, Integer>>  bags = readInput(path);
        String target = "shiny-gold";
        int count = 0;
        for (String bag :  bags.keySet()) {
            if (searchBags(bags, bag, target)) count++;
        }

        System.out.printf("%s bags can contain %s\n", count, target);
        System.out.printf("1 shiny gold bag contains %s other bags\n", countBags(bags, target) - 1);
    }

    public static HashMap<String, HashMap<String, Integer>>  readInput(String path) {
        HashMap<String, HashMap<String, Integer>>  bags = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line, key;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(" ");
                key = String.join("-", Arrays.copyOfRange(s, 0, 2));
                if (!Arrays.asList(s).contains("other")) {
                    bags.put(key, new HashMap<>());
                    for (int i = 4; i < s.length; i += 4) {
                        bags.get(key).put(String.join("-", Arrays.copyOfRange(s, i + 1, i + 3)), Integer.parseInt(s[i]));
                    }
                }
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bags;
    }

    public static boolean searchBags(HashMap<String, HashMap<String, Integer>> bags, String bag, String target) {
        if (!bags.containsKey(bag)) {
            return false;
        } else {
            HashMap<String, Integer> sbags = bags.get(bag);
            if (sbags.containsKey(target)) {
                return true;
            } else {
                for (String sbag : sbags.keySet()) {
                    if (searchBags(bags, sbag, target)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int countBags(HashMap<String, HashMap<String, Integer>> bags, String bag) {
        int result = 0;
        if (!bags.containsKey(bag)) {
            return 1;
        }
        result ++;
        HashMap<String, Integer> sbags = bags.get(bag);
        for (String sbag : sbags.keySet()) {
            for (int i = 0; i < sbags.get(sbag); i++) {
                result += countBags(bags, sbag);
            }
        }
        return result;
    }

}
