package io.nayra.halinterpreter;

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
