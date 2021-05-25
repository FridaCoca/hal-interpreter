package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;
import io.nayra.halinterpreter.Main;

public class OutInstruction extends Instruction {

    int instructionIndex;
    private int number = 0;


    public OutInstruction(int instructionIndex, int number) {
        this.instructionIndex = instructionIndex;
        this.number = number;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        halProcessor.pc = halProcessor.pc + 1;

        if (number == 0) {
            halProcessor.io0 = halProcessor.accu;
            halProcessor.debugPrint("io0: " + halProcessor.io0);
        } else {
            halProcessor.io1 = halProcessor.accu;
            halProcessor.debugPrint("io1: " + halProcessor.io1);
        }

    }

    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
