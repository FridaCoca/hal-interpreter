package io.nayra.halinterpreter;

public class LoadInstruction extends Instruction{
    int registerNumber;

    public LoadInstruction(int registerNumber){
        this.registerNumber = registerNumber;
    }
    @Override
    void run() {
        Main.accu = Main.registry[registerNumber];
    }
}
