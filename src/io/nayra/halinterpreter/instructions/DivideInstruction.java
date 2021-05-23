package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;

public class DivideInstruction extends Instruction {
    int registerNumber;
    int instructionIndex;

    public DivideInstruction(int instructionIndex, int registerNumber) {
        this.registerNumber = registerNumber;
        this.instructionIndex = instructionIndex;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        halProcessor.accu = (halProcessor.accu / halProcessor.registers[registerNumber]);
        halProcessor.pc = halProcessor.pc + 1;
    }

    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
