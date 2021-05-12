package io.nayra.halinterpreter;

public class RemoveInstruction extends Instruction {
    int instructionIndex;
    private final int registryNumber;

    public RemoveInstruction(int instructionIndex, int registryNumber) {
        this.instructionIndex = instructionIndex;
        this.registryNumber = registryNumber;
    }

    @Override
    void run() {
        float value = Main.registers[registryNumber];
        Main.accu = Main.accu - value;

    }
    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
