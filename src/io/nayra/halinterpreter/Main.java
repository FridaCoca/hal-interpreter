//package io.nayra.halinterpreter;

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
    static float accu = 20;
    static int pc = 0;
    static float io0 = 5;
    static float io1 = 6;
    static boolean debugMode = false;
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
        System.out.println("Debug enabled: " + debugMode);

        Arrays.fill(memory, 5);//initializes Elements of registry with 0
        memory[2] = 3;

        Arrays.fill(registers, 0);//initializes Elements of registry with 0
        registers[1] = 2;//for debugging
        registers[2] = 2;


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

    static void interpret(String program) {
        String[] instructions = program.split("\n");
        for (String instruction : instructions) {
            Instruction o = parseInstruction(instruction);
            System.out.println(o);
            if (o != null) o.run();
        }
    }

    static void interpret(ArrayList<String> script) {
        for (String instruction : script) {
            Instruction o = parseInstruction(instruction);
            Class c = o.getClass();
            // System.out.println(o);
            if (debugMode) printForDebug(c);
                if (o != null) o.run();
            if (debugMode) printForDebug(c);
        }
    }

    static boolean classUsesRegister(Instruction o) {
        Class c = o.getClass();
        if (c == AddInstruction.class) return true;
        else if (c == SubInstruction.class) return true;
        else if (c == MultiplicationInstruction.class) return true;
        else if (c == DivideInstruction.class) return true;
        else if (c == LoadInstruction.class) return true;
            //else if (c == StoreInDInstruction.class) return true;
        else return false;
    }

    private static void printForDebug(Class c) {

        System.out.println("ACCU: " + accu);
        if(neededRegister != -1){
            System.out.println("REG" + neededRegister + ": " + registers[neededRegister]);
        }
        System.out.println("PC: " + pc);
        System.out.println("----------------------------------------");

    }

    static Instruction parseInstruction(String instruction) {
        String[] tokens = instruction.split(" ");
        if (debugMode) printCommand(tokens);
        neededRegister = -1;

        switch (tokens[1]) {
            case "START": {
                return new StartInstruction();
            }
            case "STOP": {
                return new StopInstruction();
            }
            case "OUT": {
                int ioNumber = Integer.parseInt(tokens[2]);
                return new OutInstruction(ioNumber);
            }
            case "IN": {
                int ioNumber = Integer.parseInt(tokens[2]);
                return new InInstruction(ioNumber);
            }
            case "LOAD": {
                neededRegister = Integer.parseInt(tokens[2]);
                return new LoadInstruction(neededRegister);
            }
            case "LOADNUM": {
                float value = Integer.parseInt(tokens[2]);
                return new LoadNumberInstruction(value);
            }
            case "STORE": {
                neededRegister = Integer.parseInt(tokens[2]);
                return new StoreInstruction(neededRegister);
            }
            case "JUMPNEG": {
                int programmSpeicherAddr = Integer.parseInt(tokens[2]);
                return new JumpNegInstruction(programmSpeicherAddr);
            }
            case "JUMPPOS": {
                int programmSpeicherAddr = Integer.parseInt(tokens[2]);
                return new JumpPosInstruction(programmSpeicherAddr);
            }
            case "JUMPNULL": {
                int programmSpeicherAddr = Integer.parseInt(tokens[2]);
                return new JumpNullInstruction(programmSpeicherAddr);
            }
            case "JUMP": {
                int programmSpeicherAddr = Integer.parseInt(tokens[2]);
                return new JumpInstruction(programmSpeicherAddr);
            }
            case "ADD": {
                neededRegister = Integer.parseInt(tokens[2]);
                return new AddInstruction(neededRegister);
            }
            case "ADDNUM": {
                int value = Integer.parseInt(tokens[2]);
                return new AddNumInstruction(value);
            }
            case "SUB": {
                int value = Integer.parseInt(tokens[2]);
                return new SubInstruction(value);
            }
            case "MUL": {
                neededRegister = Integer.parseInt(tokens[2]);
                return new MultiplicationInstruction(neededRegister);
            }
            case "DIV": {
                neededRegister = Integer.parseInt(tokens[2]);
                return new DivideInstruction(neededRegister);
            }
            case "SUBNUM": {
                float value = Integer.parseInt(tokens[2]);
                return new SubNumberInstruction(value);
            }
            case "MULNUM": {
                float value = Integer.parseInt(tokens[2]);
                return new MultiplicationNumberInstruction(value);
            }
            case "DIVNUM": {
                float value = Integer.parseInt(tokens[2]);
                return new DivideNumberInstruction(value);
            }
            case "REMOVE": {
                neededRegister = Integer.parseInt(tokens[2]);
                return new RemoveInstruction(neededRegister);
            }
            case "LOADIND" : {
                neededRegister = Integer.parseInt(tokens[2]);
                return new LoadIndInstruction(neededRegister);
            }
            case "STOREIND" : {
                neededRegister = Integer.parseInt(tokens[2]);
                return new StoreIndInstruction(neededRegister);
            }
            default:
                return null;
        }
    }

    private static void printCommand(String[] tokens) {
        System.out.println("----------------------------------------");
        if (tokens.length == 3) {
            System.out.println(tokens[1] + " " + tokens[2]);
        } else {
            System.out.println(tokens[1]);
        }
    }
}
