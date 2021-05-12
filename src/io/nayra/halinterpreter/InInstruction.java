package io.nayra.halinterpreter;

public class InInstruction extends Instruction {
    private int number;
    int instructionIndex;

    public InInstruction(int number, int instructionIndex) {
        this.number = number;
        this.instructionIndex = instructionIndex;
    }

    @Override
    void run() {
        if (number == 0) {
            Main.accu = Main.io0;
        } else {
            Main.accu = Main.io1;
        }
        System.out.println("ACCU: " + Main.accu);

        Main.pc = Main.pc + 1;
    }

    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
