package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;
import io.nayra.halinterpreter.Main;

public class StoreIndInstruction extends Instruction {
    int instructionIndex;
    int register;

    public StoreIndInstruction(int instructionIndex, int register) {
        this.instructionIndex = instructionIndex;
        this.register = register;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        halProcessor.memory[register] = halProcessor.accu;
        halProcessor.pc = halProcessor.pc + 1;
        
    }
    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}

