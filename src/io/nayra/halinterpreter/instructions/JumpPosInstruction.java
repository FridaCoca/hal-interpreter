package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;
import io.nayra.halinterpreter.Main;

public class JumpPosInstruction extends Instruction{
    int instructionIndex;
    int programmSpeicherAddr;

    public JumpPosInstruction(int instructionIndex, int programmSpeicherAddr) {
        this.instructionIndex = instructionIndex;
        this.programmSpeicherAddr = programmSpeicherAddr;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        if(halProcessor.accu > 0){
            halProcessor.pc = programmSpeicherAddr;
        }
    }
    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
