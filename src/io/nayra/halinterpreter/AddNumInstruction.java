package io.nayra.halinterpreter;

public class AddNumInstruction extends Instruction {
    float value;
    int instructionIndex;

    public AddNumInstruction(int instructionIndex, float value) {
        this.value = value;
        this.instructionIndex = instructionIndex;
    }

    @Override
    void run() {
        Main.accu = Main.accu + value;
        Main.pc = Main.pc +1;
    }

    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
