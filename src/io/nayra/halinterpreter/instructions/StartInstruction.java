package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;
import io.nayra.halinterpreter.Main;

public class StartInstruction extends Instruction {

    int instructionIndex;

    public StartInstruction(int instructionIndex) {
        this.instructionIndex = instructionIndex;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        halProcessor.debugPrint("Program started");
        halProcessor.pc = halProcessor.pc + 1;

    }
    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
