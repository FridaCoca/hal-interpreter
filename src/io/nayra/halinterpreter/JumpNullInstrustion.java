package io.nayra.halinterpreter;

public class JumpNullInstrustion extends Instruction{
    int programmSpeicherAddr;

    public JumpNullInstrustion(int programmSpeicherAddr) {
        this.programmSpeicherAddr = programmSpeicherAddr;
    }

    @Override
    void run() {
        if (Main.accu == 0 ){
            Main.pc = programmSpeicherAddr;
        }

    }
}
