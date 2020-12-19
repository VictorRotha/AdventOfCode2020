package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day18 {

    public static void main(String[] args) {
        String path = "src/input/Day18_input.txt";
        System.out.printf("Part 01 sum is %s\n", readInput(path, false));
        System.out.printf("Part 02 sum is %s\n", readInput(path, true));

    }

    public static long readInput(String path, boolean advanced) {
        long result = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                result += runExpression(line.strip(), advanced);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String applyParas(String input) {
        String result = "";
        int oPara = 0;
        boolean changed= false;
        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if (c == '*' && oPara == 0) {
                result += ") * (";
                changed = true;
            } else if (c == '(') {
                oPara++;
                result += c;
            } else if (c == ')') {
                oPara--;
                result += c;
            } else if (c != ' ') {
                result += c;
            }

        }
        if (changed) {
            result = "(" + result + ")";
        }
        return result;
    }

    public static long runExpression(String input, boolean advanced) {
        if (advanced) input = applyParas(input);
        ArrayList<String[]> split = splitExpression(input);

        long result = 0;
        for (String[] e : split) {
            long p;
            if (e[0].contains("+") || e[0].contains("*")) {
                p = runExpression(e[0], advanced);
            } else {
                p = Integer.parseInt(e[0]);
            }
            result = (e[1].equals("*")) ? result * p : result + p;
        }
        return result;
    }

    public static ArrayList<String[]> splitExpression(String input) {
        int idx = 0, nxtIdx;
        int nxtOpen, nxtClosed, oPara;
        String op = "+";
        ArrayList<String[]> result = new ArrayList<>();

        while (idx < input.length()) {
            char c = input.charAt(idx);
            if (c == ' ') {
                idx++;
                continue;
            }

            if (c == '(') {
                nxtIdx = idx + 1;
                oPara = 1;
                while (true) {
                    nxtOpen = input.indexOf('(', nxtIdx);
                    nxtClosed = input.indexOf(')', nxtIdx);
                    if (nxtClosed < nxtOpen || nxtOpen == -1) {
                        oPara--;
                        nxtIdx = nxtClosed + 1;
                    } else {
                        oPara++;
                        nxtIdx = nxtOpen + 1;
                    }

                    if (oPara == 0) {
                        String sub = input.substring(idx+1, nxtIdx-1);
                        result.add(new String[]{sub, op});
                        idx = nxtIdx;
                        break;
                    }
                }

            } else if (c == '+' || c == '*') {
                op = c + "";
                idx += 1;

            } else {
                result.add(new String[]{c + "", op});
                idx++;
            }
        }
        return result;
    }



}
