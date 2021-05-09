package io.nayra.halinterpreter;

public class JumpNegInstruction extends Instruction {
    int programmSpeicherAddr;

    public JumpNegInstruction(int programmSpeicherAddr) {
        this.programmSpeicherAddr = programmSpeicherAddr;
    }

    @Override
    void run() {
        if (Main.accu < 0 ){
            Main.pc = programmSpeicherAddr;
        }

    }
}
