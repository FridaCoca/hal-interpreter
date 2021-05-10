package io.nayra.halinterpreter;

public class SubInstruction extends Instruction {
    int registerNumber;

    public SubInstruction(int registerNumber) {
        this.registerNumber = registerNumber;
    }

    @Override
    void run() {
        Main.accu = Main.accu - Main.registers[registerNumber];
        Main.pc = Main.pc + 1;

    }
}
