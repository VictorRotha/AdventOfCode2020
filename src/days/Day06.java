package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day06 {

    public static void main(String[] args) {
        String path = "src/input/Day06_input.txt";

        System.out.println("Part 01: Sum is " + readInput1(path));
        System.out.println("Part 02: Sum is " + readInput2(path));
    }

    public static int readInput1(String path) {
        int sum = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            HashSet<Character> q = new HashSet<>();
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    sum += q.size();
                    q.clear();
                }
                for (int i = 0; i < line.strip().length(); i++) {
                    q.add(line.charAt(i));
                }
            }
            if (!q.isEmpty()) {
                sum += q.size();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public static int readInput2(String path) {
        int sum = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            ArrayList<Character> q = new ArrayList<>();
            boolean newGroup = true;
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    sum += q.size();
                    q.clear();
                    newGroup = true;
                    continue;
                }

                if (newGroup) {
                    for (int i = 0; i < line.strip().length(); i++) {
                        q.add(line.charAt(i));
                    }
                    newGroup = false;
                } else {
                    for (int i = 0; i < q.size(); i++) {
                        Character c = q.get(i);
                        if (!line.contains(String.valueOf(q.get(i)))) {
                            q.remove((c));
                            i -= 1;
                        }
                    }
                }
            }
            if (!q.isEmpty()) {
                sum += q.size();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;
    }

}
