//package io.nayra.halinterpreter;

public class DivideNumberInstruction extends Instruction{
    float value;

    public DivideNumberInstruction(float value) {
        this.value = value;
    }

    @Override
    void run() {
        Main.accu = Main.accu / value;
        Main.pc = Main.pc +1;
    }
}
