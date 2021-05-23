package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.Main;

public class StoreInstruction extends Instruction {
    int instructionIndex;
    int registerNumber;

    public StoreInstruction(int instructionIndex, int registerNumber) {
        this.instructionIndex = instructionIndex;
        this.registerNumber = registerNumber;
    }

    @Override
    void run() {
        Main.registers[registerNumber] = Main.accu;
        Main.pc = Main.pc + 1;
    }

    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
