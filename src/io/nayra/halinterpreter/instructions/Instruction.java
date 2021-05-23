package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;

public abstract class Instruction {
    public abstract void run(HalProcessor halProcessor);

    public abstract int getInstructionIndex();
}
