//package io.nayra.halinterpreter;

public class OutInstruction extends Instruction {
    private int number = 0;

    public OutInstruction(int number) {
        this.number = number;
    }

    @Override
    void run() {
        if (number == 0) {
            Main.io0 = Main.accu;
            System.out.println("io0: " + Main.io0);
        } else {
            Main.io1 = Main.accu;
            System.out.println("io1: " + Main.io1);
        }
        Main.pc = Main.pc + 1;

    }
}
