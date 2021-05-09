package io.nayra.halinterpreter;

public class LoadNumberInstruction extends Instruction {
    float value;

    public LoadNumberInstruction(float value) {
        this.value = value;
    }

    @Override
    void run() {
        // TODO stores the value in accu
    }
}
