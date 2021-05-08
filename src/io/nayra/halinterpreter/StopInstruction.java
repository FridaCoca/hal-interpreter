package io.nayra.halinterpreter;

public class StopInstruction extends Instruction {
    @Override
    void run() {
        System.out.println("Program stopped");
    }
}
