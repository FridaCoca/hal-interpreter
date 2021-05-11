//package io.nayra.halinterpreter;

public class DivideInstruction extends Instruction {
    int registerNumber;

    public DivideInstruction(int registerNumber) {
        this.registerNumber = registerNumber;
    }

    @Override
    void run() {
        Main.accu = Main.accu / Main.registers[registerNumber];
        Main.pc = Main.pc + 1;

    }
}
