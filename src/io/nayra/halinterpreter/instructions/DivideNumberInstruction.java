package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.Main;

public class DivideNumberInstruction extends Instruction{

    int instructionIndex;
    float value;

    public DivideNumberInstruction(int instructionIndex, float value) {
        this.instructionIndex = instructionIndex;
        this.value = value;
    }

    @Override
    void run() {
        Main.accu = Main.accu / value;
        Main.pc = Main.pc +1;
    }

    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }

}
