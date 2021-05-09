package io.nayra.halinterpreter;

public class StoreInstruction extends Instruction{
    int registerNumber;

    public StoreInstruction(int registerNumber) {
        this.registerNumber = registerNumber;
    }

    @Override
    void run() {
        Main.registry[registerNumber] = Main.accu;
        Main.accu = 0;
        Main.pc = Main.pc +1;
    }
}
