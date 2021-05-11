package io.nayra.halinterpreter;

public class JumpInstruction extends Instruction {

    int programmSpeicherAddr;

    public JumpInstruction(int programmSpeicherAddr) {
        this.programmSpeicherAddr = programmSpeicherAddr;
    }

    @Override
    void run() {
        Main.pc = programmSpeicherAddr;
    }
}
