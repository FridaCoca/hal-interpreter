package io.nayra.halinterpreter;

public class RemoveInstruction extends Instruction {
    private final int registryNumber;

    public RemoveInstruction(int registryNumber) {
        this.registryNumber = registryNumber;
    }

    @Override
    void run() {
        float value = Main.registry[registryNumber];
        Main.accu = Main.accu - value;

    }
}
