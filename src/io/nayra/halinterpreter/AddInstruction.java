package io.nayra.halinterpreter;

public class AddInstruction extends Instruction {
    private final int registerNumber;
    int instructionIndex;


    public AddInstruction(int registerNumber, int instructionIndex) {
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
