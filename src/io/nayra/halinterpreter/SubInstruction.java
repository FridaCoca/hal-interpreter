package io.nayra.halinterpreter;

public class SubInstruction extends Instruction {
    int instructionIndex;
    int registerNumber;

    public SubInstruction(int instructionIndex, int registerNumber) {
        this.instructionIndex = instructionIndex;
        this.registerNumber = registerNumber;
    }

    @Override
    void run() {
        Main.accu = Main.accu - Main.registers[registerNumber];
        Main.pc = Main.pc + 1;

    }
    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
