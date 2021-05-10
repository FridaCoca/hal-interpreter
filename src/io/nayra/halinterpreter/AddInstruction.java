package io.nayra.halinterpreter;

public class AddInstruction extends Instruction {
    private final int registerNumber;

    public AddInstruction(int registerNumber) {
        this.registerNumber = registerNumber;
    }

    @Override
    void run() {
        float value = Main.registers[registerNumber];
        Main.accu = Main.accu + value;
    }
}
