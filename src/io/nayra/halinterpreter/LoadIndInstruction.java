package io.nayra.halinterpreter;
public class LoadIndInstruction extends Instruction{
    int instructionIndex;
    int register;

    public LoadIndInstruction(int instructionIndex, int register) {
        this.instructionIndex = instructionIndex;
        this.register = register;
    }

    @Override
    void run() {
        Main.accu = Main.memory[register];
        Main.pc = Main.pc + 1;
        
    }
    @Override
    int getInstructionIndex() {
        return instructionIndex;
    }
}
