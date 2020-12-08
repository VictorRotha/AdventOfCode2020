package days;

import data.Day08Instruction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day08 {

    public static void main(String[] args) {
        String path = "src/input/Day08_input.txt";

        ArrayList<Day08Instruction> instructions = readInput(path);
        int[] result = runInstructions(instructions);
        System.out.println("Part 01: Accumulator value is " + result[1]);
        System.out.println("Part 02: Fixed Accumulator value is: " + searchFix(instructions));
    }

    public static ArrayList<Day08Instruction> readInput(String path) {
        ArrayList<Day08Instruction> instructions = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                instructions.add(new Day08Instruction(line.split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instructions;
    }


    public static int[] runInstructions(ArrayList<Day08Instruction> instructions) {
        return runInstructions(instructions, false, 0);
    }

    public static int[] runInstructions(ArrayList<Day08Instruction> instructions, boolean modified, int modIndex) {
        ArrayList<Integer> instrDone = new ArrayList<>();
        String op; int ar;

        int index = 0;
        int instrEnd = 0;
        int accumulator = 0;
        while (!instrDone.contains(index)) {
            if (index >= instructions.size()) {
                instrEnd = 1;
                break;
            }
            instrDone.add(index);

            if (modified && index == modIndex) {
                op = (instructions.get(index).operation.equals("nop")) ? "jmp" : "nop";
            } else {
                op = instructions.get(index).operation;
            }

            ar = instructions.get(index).argument;
            switch (op) {
                case "acc":
                    accumulator += ar;
                    index++;
                    break;
                case "jmp":
                    index += ar;
                    break;
                case "nop":
                    index++;
                    break;
            }
        }

        return new int[] {instrEnd, accumulator};
    }

    public static int searchFix(ArrayList<Day08Instruction> instructions) {

        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i).operation.equals("acc")) {
                continue;
            }
            int[] result = runInstructions(instructions, true, i);
            if (result[0] == 1) {
                return result[1];
            }
        }
    return 0;
    }
}
