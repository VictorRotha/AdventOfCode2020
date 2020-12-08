package data;

public class Day08Instruction {

    public String operation;
    public int argument;

    public Day08Instruction(String op, int arg) {
        operation = op;
        argument = arg;
    }

    public Day08Instruction(String[] args) {
        this(args[0], Integer.parseInt(args[1]));
    }

    @Override
    public String toString() {
        return String.format("(op: %s arg: %s)", operation, argument);
    }
}
