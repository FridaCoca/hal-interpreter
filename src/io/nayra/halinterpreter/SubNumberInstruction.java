package io.nayra.halinterpreter;

public class SubNumberInstruction extends Instruction{
    int instructionIndex;
    float value;

    public SubNumberInstruction(int instructionIndex, float value) {
        this.instructionIndex = instructionIndex;
        this.value = value;
    }

    @Override
    void run() {
        Main.accu = Main.accu - value;
        Main.pc = Main.pc +1;
    }

    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
