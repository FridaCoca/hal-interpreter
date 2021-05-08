package io.nayra.halinterpreter;

public class AddInstruction extends Instruction {
    private final int registerNumber;

    public AddInstruction(int registryNumber) {
        this.registerNumber = registryNumber;
    }

    @Override
    void run() {
        float value = Main.registry[registerNumber];
        Main.accu = Main.accu + value;
    }
}
