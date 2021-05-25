package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;
import io.nayra.halinterpreter.Main;

public class JumpInstruction extends Instruction {

    int instructionIndex;
    int programmSpeicherAddr;

    public JumpInstruction(int instructionIndex, int programmSpeicherAddr) {
        this.instructionIndex = instructionIndex;
        this.programmSpeicherAddr = programmSpeicherAddr;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        halProcessor.pc = programmSpeicherAddr;
    }

    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
