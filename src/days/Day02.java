package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day02 {

    public static void main(String[] args) {

        String path = "src/input/Day02_input.txt";
        int minCount, maxCount, cCount, validCount1, validCount2;
        String password;
        char c, c1, c2;
        boolean policy1, policy2;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;

            validCount1 = 0; validCount2 = 0;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(" ");

                minCount = Integer.parseInt(fields[0].split("-")[0]);
                maxCount = Integer.parseInt(fields[0].split("-")[1]);
                c = fields[1].charAt(0);
                password = fields[2];

                cCount = 0;
                for (int i = 0; i < password.length(); i++) {
                    if (password.charAt(i) == c) cCount ++;
                }

                policy1 = (cCount >= minCount && cCount <= maxCount);
                if (policy1) validCount1++;

                c1 = password.charAt(minCount-1);
                c2 = password.charAt(maxCount-1);
                policy2 = ((c1 == c) || (c2 == c)) && (c1 != c2);
                if (policy2) validCount2++;

            }

            System.out.println("Valid Passwords 1: " + validCount1);
            System.out.println("Valid Passwords 2: " + validCount2);








        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
