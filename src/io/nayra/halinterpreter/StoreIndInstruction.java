package io.nayra.halinterpreter;
public class StoreIndInstruction extends Instruction {
    int instructionIndex;
    int register;

    public StoreIndInstruction(int instructionIndex, int register) {
        this.instructionIndex = instructionIndex;
        this.register = register;
    }

    @Override
    void run() {
        Main.memory[register] = Main.accu;
        Main.pc = Main.pc + 1;
        
    }
    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}

