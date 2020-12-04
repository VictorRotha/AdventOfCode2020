package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day04 {

    public static void main(String[] args) {
        String path = "src/input/Day04_input.txt";
        ArrayList<HashMap<String, String>> input = readInput(path);

        int valid = 0;
        for (HashMap<String, String> pass : input) {
            if (pass.size() == 8 || (pass.size() == 7 && !pass.containsKey("cid")))  {
                valid += 1;
            }
        }

        System.out.printf("Part 01: %s of %s passports are valid\n", valid, input.size());

    }

    public static HashMap<String, String> getPassport(String entry) {
        HashMap<String, String> passport = new HashMap<>();
        for (String pass : entry.split(" ")) {
            passport.put(pass.split(":")[0], pass.split(":")[1]);
        }
        return passport;
    }

    public static ArrayList<HashMap<String, String>> readInput(String path) {

        ArrayList<HashMap<String, String>> input = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            String entry = "";
            while ((line = br.readLine()) != null) {
                entry += line.strip() + " ";
                if (line.equals("")) {
                    input.add(getPassport(entry));
                    entry = "";
                }
            }
            input.add(getPassport(entry));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return input;



    }




}
