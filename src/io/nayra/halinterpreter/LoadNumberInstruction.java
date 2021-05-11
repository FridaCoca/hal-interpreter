//package io.nayra.halinterpreter;

public class LoadNumberInstruction extends Instruction {
    float value;

    public LoadNumberInstruction(float value) {
        this.value = value;
    }

    @Override
    void run() {
        Main.accu = value;
        Main.pc = Main.pc + 1;

    }
}
