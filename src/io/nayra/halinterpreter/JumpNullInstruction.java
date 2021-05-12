package io.nayra.halinterpreter;

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
        }
    }
    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
