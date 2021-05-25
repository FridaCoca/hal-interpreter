package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;
import io.nayra.halinterpreter.Main;

public class InInstruction extends Instruction {
    private int number;
    int instructionIndex;

    public InInstruction(int instructionIndex, int number) {
        this.number = number;
        this.instructionIndex = instructionIndex;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        halProcessor.pc = halProcessor.pc + 1;

        if (number == 0) {
            halProcessor.accu = halProcessor.io0;
        } else {
            halProcessor.accu = halProcessor.io1;
        }
        //Main.debugPrint("ACCU: " + Main.accu);
    }

    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
