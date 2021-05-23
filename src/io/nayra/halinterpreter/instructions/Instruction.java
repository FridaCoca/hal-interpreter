package io.nayra.halinterpreter.instructions;

public abstract class Instruction {
    public abstract void run();

    public abstract int getInstructionIndex();
}
