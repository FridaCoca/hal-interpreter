package io.nayra.halinterpreter;

public class MultiplicationInstruction extends Instruction {
    int registerNumber;

    public MultiplicationInstruction(int registerNumber) {
        this.registerNumber = registerNumber;
    }

    @Override
    void run() {
        Main.accu = Main.accu * Main.registry[registerNumber];
        Main.pc = Main.pc + 1;
    }
}
