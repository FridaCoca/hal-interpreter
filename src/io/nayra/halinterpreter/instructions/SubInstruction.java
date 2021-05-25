package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;
import io.nayra.halinterpreter.Main;

public class SubInstruction extends Instruction {
    int instructionIndex;
    int registerNumber;

    public SubInstruction(int instructionIndex, int registerNumber) {
        this.instructionIndex = instructionIndex;
        this.registerNumber = registerNumber;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        halProcessor.accu = halProcessor.accu - halProcessor.registers[registerNumber];
        halProcessor.pc = halProcessor.pc + 1;

    }
    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
