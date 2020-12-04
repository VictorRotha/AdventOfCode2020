package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day04 {

    public static void main(String[] args) {
        String path = "src/input/Day04_input.txt";
        ArrayList<HashMap<String, String>> input = readInput(path);

        int valid1 = 0; int valid2 = 0;
        String[] validEcls = {"amb", "blu","brn", "gry", "grn", "hzl", "oth"};
        for (HashMap<String, String> pass : input) {
            if (pass.size() == 8 || (pass.size() == 7 && !pass.containsKey("cid")))  {
                valid1 += 1;

                String hgt = pass.get("hgt");
                int hgtNumber = Integer.parseInt(hgt.substring(0,hgt.length()-2));
                if ((Integer.parseInt(pass.get("byr")) < 1920 || Integer.parseInt(pass.get("byr")) > 2002)
                    || (Integer.parseInt(pass.get("iyr")) < 2010 || Integer.parseInt(pass.get("iyr")) > 2020)
                    || (Integer.parseInt(pass.get("eyr")) < 2020 || Integer.parseInt(pass.get("eyr")) > 2030)
                    || (!pass.get("hcl").matches("#[a-f0-9]{6}"))
                    || (!Arrays.asList(validEcls).contains(pass.get("ecl")))
                    || (!pass.get("pid").matches("[0-9]{9}"))
                    || (hgt.endsWith("cm") && (hgtNumber < 150 || hgtNumber > 193))
                    || (hgt.endsWith("in") && (hgtNumber < 59 || hgtNumber > 76))
                    || (!hgt.endsWith("in") && !hgt.endsWith("cm"))
                ) continue;

                valid2 ++;
            }
        }
        System.out.printf("Part 01: %s of %s passports are valid\n", valid1, input.size());
        System.out.printf("Part 02: %s of %s passports are valid\n", valid2, input.size());
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
