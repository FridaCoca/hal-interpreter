package io.nayra.halinterpreter.instructions;
import io.nayra.halinterpreter.HalProcessor;
import io.nayra.halinterpreter.Main;

import java.util.Scanner;

public class InInstruction extends Instruction {
    private int s;
    int instructionIndex;

    public InInstruction(int instructionIndex, int s) {
        this.s = s;
        this.instructionIndex = instructionIndex;
    }

    @Override
    public void run(HalProcessor halProcessor) {
        halProcessor.pc = halProcessor.pc + 1;

        if(s == 0){
            System.out.println("Please enter your first value: ");
            // Using Console to input data from user
            //String input = System.console().readLine();
            //halProcessor.accu = Float.parseFloat(input);

            // Using Scanner for Getting Input from User
            Scanner in = new Scanner(System.in);
            halProcessor.accu = in.nextFloat();
            // closing scanner
            in.close();
            //System.out.println("You entered: " + halProcessor.io0);

            System.out.println("You entered: " + halProcessor.accu);
        }else{
            if(halProcessor.inputLinks.containsKey(s)){
                halProcessor.accu = halProcessor.inputLinks.get(s).get();
            }else {
                throw new IllegalArgumentException("IN-Register " + s + " does not exist!");
            }
        }




       /*if (number == 0) {
           halProcessor.accu = halProcessor.io0;
       } else {
           halProcessor.accu = halProcessor.io1;
       }*/
        //Main.debugPrint("ACCU: " + Main.accu);
    }

    @Override
    public int getInstructionIndex() {
        return instructionIndex;
    }
}


