package io.nayra.halinterpreter;

public class AddNumInstruction extends Instruction {
    float value;
    int instructionIndex;

    public AddNumInstruction(float value, int instructionIndex) {
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
