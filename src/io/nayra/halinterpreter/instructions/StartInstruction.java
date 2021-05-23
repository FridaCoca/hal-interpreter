package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.Main;

public class StartInstruction extends Instruction {

    int instructionIndex;

    public StartInstruction(int instructionIndex) {
        this.instructionIndex = instructionIndex;
    }

    @Override
    void run() {
        Main.debugPrint("Program started");
        Main.pc = Main.pc + 1;

    }
    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
