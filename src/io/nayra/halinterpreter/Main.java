package io.nayra.halinterpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
//import java.util.Scanner;

public class Main {
    static float[] memory = new float[100];
    static float[] registers = new float[16];//static makes string[] accessible from everywhere
    static float accu = 0;
    static int pc = 0;
    static float io0 = 0;
    static float io1 = 0;
    static boolean debugMode = true;
    static int neededRegister = -1;


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        /*System.out.println("Soll der Debug Mode aktiviert werden?");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        if(input == "Yes"){
            //debugMode = true;
            
        }
        else{
            //debugMode = false;
            System.out.println("False");
        }*/
        
        
        /*char argument;
        String file;
        for(int i = 0; i < args.length; i++){
            
            argument = args[i].charAt(0);
            if(args[i].equals("d")){
                System.out.println("Debug");
                debugMode = true;
            }
            String filename;
            if(argument == '.'){
                System.out.println("Pfad");
                filename = args[i];
            }
            try{
                file = filename;
            }catch (Exception e) {
                e.printStackTrace();

            }
            

        }*/

        debugMode = Arrays.asList(args).contains("-d");
        System.out.println("Debug enabled: " + debugMode)
        Arrays.fill(memory, 5);//initializes Elements of registry with 0
        memory[2] = 3;

        Arrays.fill(registers, 0);//initializes Elements of registry with 0
        //registers[1] = 2;//for debugging
        //registers[2] = 2;


        for (String a : args) {
            if (new File(a).exists()) {
                ArrayList<String> script = fileToArrayList(a);
                interpret(script);
                break;
            }
        }


        long endTime = System.currentTimeMillis();

        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        //sc.close();
    }

    static ArrayList<String> fileToArrayList(String filename) {
        ArrayList<String> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while (br.ready()) {
                result.add(br.readLine());
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    /*static void interpret(String program) {
        String[] instructions = program.split("\n");
        for (String instruction : instructions) {
            Instruction o = parseInstruction(instruction);
            System.out.println(o);
            if (o != null) o.run();
        }
    }*/

    /*static void interpret(ArrayList<String> script) {
        for (String instruction : script) {
            Instruction o = parseInstruction(instruction);
            if (o instanceof StopInstruction) break;
            Class c = o.getClass();
            if (debugMode) printForDebug(c);
            if (o.getInstructionIndex() == pc) {
                //System.out.println("Compare worked!!");
                if (o != null) o.run();
            }
            if (debugMode) printForDebug(c);
        }
    }*/

    static void interpret(ArrayList<String> script) {
        while (pc < script.size()) {
            Instruction o = parseInstruction(script.get(pc));
            Class c = o.getClass();
            if (o instanceof StopInstruction) break;
            if (debugMode) printForDebug(c);
            if (o.getInstructionIndex() == pc) {
                //System.out.println("Compare worked!!");
                if (o != null) o.run();
            }
            if (debugMode) printForDebug(c);
        }

    }
   /* static boolean classUsesRegister(Instruction o) {
        Class c = o.getClass();
        if (c == AddInstruction.class) return true;
        else if (c == SubInstruction.class) return true;
        else if (c == MultiplicationInstruction.class) return true;
        else if (c == DivideInstruction.class) return true;
        else if (c == LoadInstruction.class) return true;
            //else if (c == StoreInDInstruction.class) return true;
        else return false;
    }*/

    private static void printForDebug(Class c) {

        System.out.println("ACCU: " + accu);
        if (neededRegister != -1) {
            System.out.println("REG" + neededRegister + ": " + registers[neededRegister]);
        }
        System.out.println("PC: " + pc);
        System.out.println("----------------------------------------");

    }

    static Instruction parseInstruction(String instruction) {
        String[] tokens = instruction.split(" ");
        //System.out.println("lola" + tokens[0]);
        if (debugMode) printCommand(tokens);
        neededRegister = -1;

        switch (tokens[1]) {
            case "START": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                return new StartInstruction(instructionIndex);
            }
            case "STOP": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                return new StopInstruction(instructionIndex);
            }
            case "OUT": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                int ioNumber = Integer.parseInt(tokens[2]);
                return new OutInstruction(instructionIndex, ioNumber);
            }
            case "IN": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                int ioNumber = Integer.parseInt(tokens[2]);
                return new InInstruction(instructionIndex, ioNumber);
            }
            case "LOAD": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                neededRegister = Integer.parseInt(tokens[2]);
                return new LoadInstruction(instructionIndex, neededRegister);
            }
            case "LOADNUM": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                float value = Float.parseFloat(tokens[2]);
                return new LoadNumberInstruction(instructionIndex, value);
            }
            case "STORE": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                neededRegister = Integer.parseInt(tokens[2]);
                return new StoreInstruction(instructionIndex, neededRegister);
            }
            case "JUMPNEG": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                int programmSpeicherAddr = Integer.parseInt(tokens[2]);
                return new JumpNegInstruction(instructionIndex, programmSpeicherAddr);
            }
            case "JUMPPOS": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                int programmSpeicherAddr = Integer.parseInt(tokens[2]);
                return new JumpPosInstruction(instructionIndex, programmSpeicherAddr);
            }
            case "JUMPNULL": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                int programmSpeicherAddr = Integer.parseInt(tokens[2]);
                return new JumpNullInstruction(instructionIndex, programmSpeicherAddr);
            }
            case "JUMP": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                int programmSpeicherAddr = Integer.parseInt(tokens[2]);
                return new JumpInstruction(instructionIndex, programmSpeicherAddr);
            }
            case "ADD": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                neededRegister = Integer.parseInt(tokens[2]);
                return new AddInstruction(instructionIndex, neededRegister);
            }
            case "ADDNUM": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                float value = Float.parseFloat(tokens[2]);
                return new AddNumInstruction(instructionIndex, value);
            }
            case "SUB": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                int value = Integer.parseInt(tokens[2]);
                return new SubInstruction(instructionIndex, value);
            }
            case "MUL": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                neededRegister = Integer.parseInt(tokens[2]);
                return new MultiplicationInstruction(instructionIndex, neededRegister);
            }
            case "DIV": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                neededRegister = Integer.parseInt(tokens[2]);
                return new DivideInstruction(instructionIndex, neededRegister);
            }
            case "SUBNUM": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                float value = Float.parseFloat(tokens[2]);
                return new SubNumberInstruction(instructionIndex, value);
            }
            case "MULNUM": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                float value = Float.parseFloat(tokens[2]);
                return new MultiplicationNumberInstruction(instructionIndex, value);
            }
            case "DIVNUM": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                float value = Float.parseFloat(tokens[2]);
                return new DivideNumberInstruction(instructionIndex, value);
            }
            case "REMOVE": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                neededRegister = Integer.parseInt(tokens[2]);
                return new RemoveInstruction(instructionIndex, neededRegister);
            }
            case "LOADIND": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                neededRegister = Integer.parseInt(tokens[2]);
                return new LoadIndInstruction(instructionIndex, neededRegister);
            }
            case "STOREIND": {
                int instructionIndex = Integer.parseInt((tokens[0]));
                neededRegister = Integer.parseInt(tokens[2]);
                return new StoreIndInstruction(instructionIndex, neededRegister);
            }
            default:
                return null;
        }
    }

    static void printCommand(String[] tokens) {
        System.out.println("----------------------------------------");
        if (tokens.length == 3) {
            System.out.println(tokens[1] + " " + tokens[2]);
        } else {
            System.out.println(tokens[1]);
        }
    }
}
