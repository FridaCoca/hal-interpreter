package io.nayra.halinterpreter;

public class OutInstruction extends Instruction {

    int instructionIndex;
    private int number = 0;


    public OutInstruction(int instructionIndex, int number) {
        this.instructionIndex = instructionIndex;
        this.number = number;
    }

    @Override
    void run() {
        Main.pc = Main.pc + 1;

        if (number == 0) {
            Main.io0 = Main.accu;
            System.out.println("io0: " + Main.io0);
        } else {
            Main.io1 = Main.accu;
            System.out.println("io1: " + Main.io1);
        }
//TO DO OUT 0 SCHNITTSTELLE
    }

    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
