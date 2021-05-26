package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;
import io.nayra.halinterpreter.Main;

public class JumpNegInstruction extends Instruction {
    int instructionIndex;
    int programmSpeicherAddr;

    public JumpNegInstruction(int instructionIndex, int programmSpeicherAddr) {
        this.instructionIndex = instructionIndex;
        this.programmSpeicherAddr = programmSpeicherAddr;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        //System.out.println( "JUMP NEG ausgefuehrt ");
        if (halProcessor.accu < 0 ){
            halProcessor.pc = programmSpeicherAddr;
        } else {
            halProcessor.pc = halProcessor.pc + 1;
        }
    }
    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
