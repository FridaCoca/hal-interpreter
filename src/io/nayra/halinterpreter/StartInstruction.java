package io.nayra.halinterpreter;

public class StartInstruction extends Instruction {

    int instructionIndex;

    public StartInstruction(int instructionIndex) {
        this.instructionIndex = instructionIndex;
    }

    @Override
    void run() {
        System.out.println("Program started");
        Main.pc = Main.pc + 1;

    }
    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
