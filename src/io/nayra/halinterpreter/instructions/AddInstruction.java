package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;

public class AddInstruction extends Instruction {
    private final int registerNumber;
    int instructionIndex;

    public AddInstruction(int instructionIndex, int registerNumber) {
        this.registerNumber = registerNumber;
        this.instructionIndex = instructionIndex;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        float value = halProcessor.registers[registerNumber];
        halProcessor.accu = halProcessor.accu + value;
        halProcessor.pc = halProcessor.pc + 1;
    }

    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
