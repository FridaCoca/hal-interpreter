package io.nayra.halinterpreter.instructions;

import io.nayra.halinterpreter.HalProcessor;
import io.nayra.halinterpreter.Main;

import java.util.Scanner;

public class StdInInstruction extends Instruction {
    int instructionIndex;

    public StdInInstruction(int instructionIndex) {
        this.instructionIndex = instructionIndex;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        System.out.println("Please enter your first value: ");
        // Using Console to input data from user
        /*String input = System.console().readLine();
        halProcessor.io0 = Float.parseFloat(input);*/

        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);
        halProcessor.io0 = in.nextFloat();
        // closing scanner
        in.close();
        //System.out.println("You entered: " + halProcessor.io0);

        halProcessor.pc = halProcessor.pc + 1;
    }

    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}
