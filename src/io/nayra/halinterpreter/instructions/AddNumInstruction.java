package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;

public class AddNumInstruction extends Instruction {
    float value;
    int instructionIndex;

    public AddNumInstruction(int instructionIndex, float value) {
        this.value = value;
        this.instructionIndex = instructionIndex;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        halProcessor.accu = halProcessor.accu + value;
        halProcessor.pc = halProcessor.pc + 1;
    }

    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
