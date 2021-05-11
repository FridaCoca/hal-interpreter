//package io.nayra.halinterpreter;

public class SubNumberInstruction extends Instruction{
    float value;

    public SubNumberInstruction(float value) {
        this.value = value;
    }

    @Override
    void run() {
        Main.accu = Main.accu - value;
        Main.pc = Main.pc +1;
    }
}
