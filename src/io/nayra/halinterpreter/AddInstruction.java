package io.nayra.halinterpreter;

public class AddInstruction extends Instruction {
    private final int registryNumber;

    public AddInstruction(int registryNumber) {
        this.registryNumber = registryNumber;
    }

    @Override
    void run() {
        float value = Main.registry[registryNumber];
        Main.accu = Main.accu + value;
    }
}
