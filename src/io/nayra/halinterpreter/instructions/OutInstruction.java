package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;


public class OutInstruction extends Instruction {

    int instructionIndex;
    private int s;


    public OutInstruction(int instructionIndex, int s) {
        this.instructionIndex = instructionIndex;
        this.s = s;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        halProcessor.pc = halProcessor.pc + 1;

        if(halProcessor.outputLinks.containsKey(s)) {
            halProcessor.outputLinks.get(s).put(halProcessor.accu);
        } else {
            throw new IllegalArgumentException("OUT-Register " + s + " does not exist!");
        }

        /*if (s == 0) {
            halProcessor.io0 = halProcessor.accu;
            halProcessor.debugPrint("io0: " + halProcessor.io0);
        } else {
            halProcessor.io1 = halProcessor.accu;
            halProcessor.debugPrint("io1: " + halProcessor.io1);
        }*/

    }

    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
