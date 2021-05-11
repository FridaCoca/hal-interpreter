//package io.nayra.halinterpreter;

public class JumpNullInstruction extends Instruction{
    int programmSpeicherAddr;

    public JumpNullInstruction(int programmSpeicherAddr) {
        this.programmSpeicherAddr = programmSpeicherAddr;
    }

    @Override
    void run() {
        if (Main.accu == 0 ){
            Main.pc = programmSpeicherAddr;
        }

    }
}
