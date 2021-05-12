package io.nayra.halinterpreter;

public abstract class Instruction {
    abstract void run();
    abstract int getInstructionIndex();
}
