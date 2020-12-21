package days;


import data.Day21Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day21 {

    public static void main(String[] args) {

        String path = "src/input/Day21_input.txt";
        Day21Input input = readInput(path);

        System.out.printf("Part 01 solution is %S\n", noAllergensSum(input));

        ArrayList<String> sortedAllergens = new ArrayList<>(input.allergens.keySet());
        Collections.sort(sortedAllergens);

        String danger = "";
        for (String alle : sortedAllergens) {
            danger += input.allergens.get(alle) + ",";
        }
        danger = danger.substring(0 ,danger.length()-1);

        System.out.printf("Part 02 solution is %S\n", danger);
    }

    public static int noAllergensSum(Day21Input input) {
        int sum = 0;
        for (String ingr : input.ingrCount.keySet()) {
            if (!input.allergens.containsValue(ingr)) {
                sum += input.ingrCount.get(ingr);
            }
        }
        return sum;

    }

    public static HashMap<String, String> findIngredient(HashMap<String, ArrayList<String>> input) {
        HashMap<String, String> result = new HashMap<>();
        HashSet<String> tmp = new HashSet<>();
        boolean changed = true;
        while (changed) {
            changed = false;
            for (String alle : input.keySet()) {
                ArrayList<String> possibleIngrs = input.get(alle);
                if (possibleIngrs.size() == 1) {
                    tmp.add(possibleIngrs.get(0));
                } else {
                    possibleIngrs.removeIf(tmp::contains);
                    changed = true;
                }
            }
        }
        for (String alle : input.keySet()) result.put(alle, input.get(alle).get(0));
        return result;
    }

    public static Day21Input readInput(String path) {
        HashMap<String, ArrayList<String>> results = new HashMap<>();
        HashMap<String, Integer> ingrCount = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                String regex = "(.*)( \\(contains )(.*)(\\))";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    String[] ingredients = matcher.group(1).split(" ");
                    String[] allergens = matcher.group(3).split(", ");
                    for (String ingr : ingredients) {
                        int count = (ingrCount.containsKey(ingr)) ? ingrCount.get(ingr) + 1 : 1;
                        ingrCount.put(ingr, count);
                    }
                    for (String alle : allergens) {
                        if (results.containsKey(alle)) {
                            results.get(alle).removeIf(ingr -> !Arrays.asList(ingredients).contains(ingr));
                        } else {
                            results.put(alle, new ArrayList<>());
                            for (String ingr : ingredients) {
                                results.get(alle).add(ingr);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Day21Input(findIngredient(results), ingrCount);
    }

}
