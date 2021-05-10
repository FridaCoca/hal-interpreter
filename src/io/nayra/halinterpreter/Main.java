package io.nayra.halinterpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static float[] registers = new float[16];//static makes string[] accessible from everywhere
    static float accu = 20;
    static int pc = 0;
    static float io0 = 5;
    static float io1 = 6;

    public static void main(String[] args) {
        Arrays.fill(registers, 0);//initializes Elements of registry with 0
        registers[1] = 2;
        registers[2] = 10;

        ArrayList<String> script = fileToArrayList("script.hal");

        interpret(script);
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
        printState();
    }

    static void interpret(ArrayList<String> script) {
        for (String instruction : script) {
            Instruction o = parseInstruction(instruction);
            System.out.println(o);
            if (o != null) o.run();
        }
        printState();
    }

    private static void printState() {
        System.out.println("ACCU: " + accu);
        System.out.println("REG2: " + registers[2]);
        System.out.println("PC: " + pc);

    }

    static Instruction parseInstruction(String instruction) {
        String[] tokens = instruction.split(" ");
        printTokens(tokens);

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
                int registerNumber = Integer.parseInt(tokens[2]);
                return new LoadInstruction(registerNumber);
            }
            case "LOADNUM": {
                float value = Integer.parseInt(tokens[2]);
                return new LoadNumberInstruction(value);
            }
            case "STORE": {
                int registerNumber = Integer.parseInt(tokens[2]);
                return new StoreInstruction(registerNumber);
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
                int registryNumber = Integer.parseInt(tokens[2]);
                return new AddInstruction(registryNumber);
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
                int registerNumber = Integer.parseInt(tokens[2]);
                return new MultiplicationInstruction(registerNumber);
            }
            case "DIV": {
                int registerNumber = Integer.parseInt(tokens[2]);
                return new DivideInstruction(registerNumber);
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
                int registryNumber = Integer.parseInt(tokens[2]);
                return new RemoveInstruction(registryNumber);
            }
            default:
                return null;
        }
    }

    private static void printTokens(String[] tokens) {
        if (tokens.length == 3) {
            System.out.println("parsing: " + tokens[1] + "-" + tokens[1]);
        } else {
            System.out.println("parsing: " + tokens[1]);
        }
    }
}
