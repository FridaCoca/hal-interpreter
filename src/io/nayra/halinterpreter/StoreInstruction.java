package io.nayra.halinterpreter;

public class StoreInstruction extends Instruction{
    int instructionIndex;
    int registerNumber;

    public StoreInstruction(int instructionIndex, int registerNumber) {
        this.instructionIndex = instructionIndex;
        this.registerNumber = registerNumber;
    }

    @Override
    void run() {
        Main.registers[registerNumber] = Main.accu;
        Main.accu = 0;
        Main.pc = Main.pc +1;
    }
    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
