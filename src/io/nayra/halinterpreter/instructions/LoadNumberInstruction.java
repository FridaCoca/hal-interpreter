package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;
import io.nayra.halinterpreter.Main;

public class LoadNumberInstruction extends Instruction {
    int instructionIndex;
    float value;

    public LoadNumberInstruction(int instructionIndex, float value) {
        this.instructionIndex = instructionIndex;
        this.value = value;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        halProcessor.accu = value;
        halProcessor.pc = halProcessor.pc + 1;

    }
    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
