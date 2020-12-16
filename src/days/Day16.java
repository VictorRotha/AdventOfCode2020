package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Day16 {

    public static void main(String[] args) {
        String path = "src/input/Day_16_input.txt";
        System.out.printf("Part 01 Error sum is %s", readInput(path));
    }

    public static int readInput(String path) {
        int result = 0;
        try {
            HashMap<String, int[]> fields = new HashMap<>();
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            int area = 0;

            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    area++;
                    continue;
                }
                if (area == 0) {
                    String fieldName = line.split(": ")[0];
                    int[] range = Arrays.stream(line.split(": ")[1].split( "-| or " )).mapToInt(Integer::parseInt).toArray();
                    fields.put(fieldName, range);

                } else if (area == 2 && !line.equals("nearby tickets:")) {
                    String[] ticket = line.split(",");
                    for (String f : ticket) {
                        if (!inFields(fields, Integer.parseInt(f))) {
                            result += Integer.parseInt(f);
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean inFields(HashMap<String, int[]> fields, int value) {

        for (int[] range : fields.values()) {
            if ((value >= range[0] && value <= range[1]) || (value >= range[2] && value <= range[3])) {
                return true;
            }
        }
        return false;
    }


}
