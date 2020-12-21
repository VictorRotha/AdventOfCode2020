package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day21 {

    public static void main(String[] args) {

        String path = "src/input/Day21_input.txt";
        readInput(path);

    }

    public static HashMap<String, ArrayList<String>> readInput(String path) {
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        HashMap<String, Integer> ingrCount = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = br.readLine()) != null) {
                String regex = "(.*)( \\(contains )(.*)(\\))";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    for (String ingr : matcher.group(1).split(" ")) {
                        int count = (ingrCount.containsKey(ingr)) ? ingrCount.get(ingr) + 1 : 1;
                        ingrCount.put(ingr, count);
                    }
                    for (String alle : matcher.group(3).split(", ")) {
                        if (result.containsKey(alle)) {
                            result.get(alle).removeIf(ingr -> !Arrays.asList(matcher.group(1).split(" ")).contains(ingr));
                        } else {
                            result.put(alle, new ArrayList<>());
                            for (String ingr : matcher.group(1).split(" ")) {
                                result.get(alle).add(ingr);
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        HashSet<String> foundIngr = new HashSet<>();
        boolean changed = true;
        while (changed) {
            changed = false;
            for (String alle : result.keySet()) {
                if (result.get(alle).size() == 1) {
                    foundIngr.add(result.get(alle).get(0));
                } else {
                    result.get(alle).removeIf(foundIngr::contains);
                    changed = true;
                }
            }
        }
        int sum = 0;
        for (String ingr : ingrCount.keySet()) {
            if (!foundIngr.contains(ingr)) {
                sum += ingrCount.get(ingr);
            }
        }


        System.out.printf("Part 01 solution is %S\n", sum);

        return result;

    }
}
