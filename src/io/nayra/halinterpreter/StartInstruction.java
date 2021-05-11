package io.nayra.halinterpreter;

public class StartInstruction extends Instruction {

    @Override
    void run() {
        System.out.println("Program started");
        Main.pc = Main.pc + 1;

    }
}
