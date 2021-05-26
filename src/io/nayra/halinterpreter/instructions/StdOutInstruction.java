package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;

public class StdOutInstruction extends Instruction {
    int instructionIndex;

    public StdOutInstruction(int instructionIndex) {
        this.instructionIndex = instructionIndex;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        System.out.println("Intermidiate Value: " + halProcessor.accu);
        halProcessor.pc = halProcessor.pc + 1;
    }

    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
