package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;
import io.nayra.halinterpreter.Main;

public class StoreInstruction extends Instruction {
    int instructionIndex;
    int registerNumber;

    public StoreInstruction(int instructionIndex, int registerNumber) {
        this.instructionIndex = instructionIndex;
        this.registerNumber = registerNumber;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        halProcessor.registers[registerNumber] = halProcessor.accu;
        halProcessor.pc = halProcessor.pc + 1;
    }

    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
