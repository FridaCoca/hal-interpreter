package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.Main;

public class LoadIndInstruction extends Instruction {
    int instructionIndex;
    int register;

    public LoadIndInstruction(int instructionIndex, int register) {
        this.instructionIndex = instructionIndex;
        this.register = register;
    }

    @Override
    public void run() {
        Main.accu = Main.memory[register];
        Main.pc = Main.pc + 1;

    }

    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
