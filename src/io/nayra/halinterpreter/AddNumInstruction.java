package io.nayra.halinterpreter;

public class AddNumInstruction extends Instruction {
    float value;

    public AddNumInstruction(float value) {
        this.value = value;
    }

    @Override
    void run() {
        Main.accu = Main.accu + value;
        Main.pc = Main.pc +1;
    }
}
