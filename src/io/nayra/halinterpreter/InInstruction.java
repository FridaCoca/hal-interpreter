package io.nayra.halinterpreter;

public class InInstruction extends Instruction {
    private int number;
    int instructionIndex;

    public InInstruction(int instructionIndex, int number) {
        this.number = number;
        this.instructionIndex = instructionIndex;
    }

    @Override
    void run() {
        Main.pc = Main.pc + 1;

        if (number == 0) {
            Main.accu = Main.io0;
        } else {
            Main.accu = Main.io1;
        }
        //Main.debugPrint("ACCU: " + Main.accu);
    }

    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
