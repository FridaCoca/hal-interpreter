package io.nayra.halinterpreter;

public class DivideInstruction extends Instruction {
    int registerNumber;
    int instructionIndex;

    public DivideInstruction(int registerNumber, int instructionIndex) {
        this.registerNumber = registerNumber;
        this.instructionIndex = instructionIndex;
    }

    @Override
    void run() {
        Main.accu = Main.accu / Main.registers[registerNumber];
        Main.pc = Main.pc + 1;

    }

    @Override
    int getInstructionIndex() {
        return 0;
    }
}
