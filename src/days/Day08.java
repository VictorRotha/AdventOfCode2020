package days;

import data.Day08Instruction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day08 {

    public static void main(String[] args) {
        String path = "src/input/Day08_input.txt";
        System.out.println("Accumulator value is " + runInstructions(readInput(path)));
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

    public static int runInstructions(ArrayList<Day08Instruction> instructions) {
        ArrayList<Integer> instrDone = new ArrayList<>();
        String op; int ar;

        int index = 0;
        int accumulator = 0;
        while (!instrDone.contains(index)) {
            instrDone.add(index);
            op = instructions.get(index).operation;
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

        return accumulator;
    }


}
