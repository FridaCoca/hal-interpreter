package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.Main;

public class JumpInstruction extends Instruction {

    int instructionIndex;
    int programmSpeicherAddr;

    public JumpInstruction(int instructionIndex, int programmSpeicherAddr) {
        this.instructionIndex = instructionIndex;
        this.programmSpeicherAddr = programmSpeicherAddr;
    }

    @Override
    void run() {
        Main.pc = programmSpeicherAddr;
    }

    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
