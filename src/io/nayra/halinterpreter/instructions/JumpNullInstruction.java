package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.Main;

public class JumpNullInstruction extends Instruction{
    int instructionIndex;
    int programmSpeicherAddr;

    public JumpNullInstruction(int instructionIndex, int programmSpeicherAddr) {
        this.instructionIndex = instructionIndex;
        this.programmSpeicherAddr = programmSpeicherAddr;
    }

    @Override
    void run() {
        if (Main.accu == 0 ){
            Main.pc = programmSpeicherAddr;
        } else { Main.pc = Main.pc + 1;}
    }

    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
