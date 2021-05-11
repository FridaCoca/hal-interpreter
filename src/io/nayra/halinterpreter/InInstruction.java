//package io.nayra.halinterpreter;

public class InInstruction extends Instruction {
    private int number;

    public InInstruction(int number) {
        this.number = number;
    }

    @Override
    void run() {
        if (number == 0) {
            Main.accu = Main.io0;
        } else {
            Main.accu = Main.io1;
        }
        System.out.println("ACCU: " + Main.accu);

        Main.pc = Main.pc + 1;
    }
}
