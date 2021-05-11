//package io.nayra.halinterpreter;

public class MultiplicationNumberInstruction extends Instruction{
   float value;

    public MultiplicationNumberInstruction(float value) {
        this.value = value;
    }

    @Override
    void run() {
        Main.accu = Main.accu * value;
        Main.pc = Main.pc +1;

    }
}
