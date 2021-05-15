package io.nayra.halinterpreter;

public class AddInstruction extends Instruction {
    private final int registerNumber;
    int instructionIndex;


    public AddInstruction(int instructionIndex, int registerNumber) {
        this.registerNumber = registerNumber;
        this.instructionIndex = instructionIndex;
    }

    @Override
    void run() {
        float value = Main.registers[registerNumber];
        Main.accu = Main.accu + value;
        Main.pc = Main.pc + 1;
    }

    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
