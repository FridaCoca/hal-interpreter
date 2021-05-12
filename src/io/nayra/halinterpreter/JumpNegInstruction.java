package io.nayra.halinterpreter;

public class JumpNegInstruction extends Instruction {
    int instructionIndex;
    int programmSpeicherAddr;

    public JumpNegInstruction(int instructionIndex, int programmSpeicherAddr) {
        this.instructionIndex = instructionIndex;
        this.programmSpeicherAddr = programmSpeicherAddr;
    }

    @Override
    void run() {
        if (Main.accu < 0 ){
            Main.pc = programmSpeicherAddr;
        }
    }
    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
