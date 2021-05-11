//package io.nayra.halinterpreter;

public class JumpPosInstruction extends Instruction{
    int programmSpeicherAddr;

    public JumpPosInstruction(int programmSpeicherAddr) {
        this.programmSpeicherAddr = programmSpeicherAddr;
    }

    @Override
    void run() {
        if(Main.accu > 0){
            Main.pc = programmSpeicherAddr;
        }

    }
}
