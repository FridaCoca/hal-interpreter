package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;
import io.nayra.halinterpreter.Main;

public class JumpNullInstruction extends Instruction{
    int instructionIndex;
    int programmSpeicherAddr;

    public JumpNullInstruction(int instructionIndex, int programmSpeicherAddr) {
        this.instructionIndex = instructionIndex;
        this.programmSpeicherAddr = programmSpeicherAddr;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        if (halProcessor.accu == 0 ){
            halProcessor.pc = programmSpeicherAddr;
        } else { halProcessor.pc = halProcessor.pc + 1;}
    }

    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
