package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;
import io.nayra.halinterpreter.Main;

public class RemoveInstruction extends Instruction {
    int instructionIndex;
    private final int registryNumber;

    public RemoveInstruction(int instructionIndex, int registryNumber) {
        this.instructionIndex = instructionIndex;
        this.registryNumber = registryNumber;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        float value = halProcessor.registers[registryNumber];
        halProcessor.accu = halProcessor.accu - value;

    }
    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
