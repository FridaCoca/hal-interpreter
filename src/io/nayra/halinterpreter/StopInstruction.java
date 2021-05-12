package io.nayra.halinterpreter;

public class StopInstruction extends Instruction {
    int instructionIndex;

    public StopInstruction(int instructionIndex) {
        this.instructionIndex = instructionIndex;
    }

    @Override
    void run() {
        System.out.println("Program stopped");
        Main.pc = Main.pc + 1;

    }
    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
