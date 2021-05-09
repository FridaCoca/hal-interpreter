package io.nayra.halinterpreter;

import java.util.Arrays;

public class Main {
    static float[] registry = new float[16];//static makes string[] accessible from everywhere
    static float accu = 0;
    static int pc = 0;
    static float io0 = 5;
    static float io1 = 6;

    public static void main(String[] args) {
        Arrays.fill(registry, 0);//initializes elems of registry with 0
        registry[1] = 2;
        registry[2] = 20;
        String program = """
                SUB 2
                
                """;
        interpret(program);
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

    private static void printState() {
        System.out.println("ACCU: " + accu);
        System.out.println("REG2: " + registry[2]);
        System.out.println("PC: " + pc);

    }

    static Instruction parseInstruction(String instruction) {
        String[] tokens = instruction.split(" ");
        printTokens(tokens);

        switch (tokens[0]) {
            case "START": {
                return new StartInstruction();
            }
            case "STOP": {
                return new StopInstruction();
            }
            case "OUT": {
                int ioNumber = Integer.parseInt(tokens[1]);
                return new OutInstruction(ioNumber);
            }
            case "IN": {
                int ioNumber = Integer.parseInt(tokens[1]);
                return new InInstruction(ioNumber);
            }
            case "LOAD": {
                int registerNumber = Integer.parseInt(tokens[1]);
                return new LoadInstruction(registerNumber);
            }
            case "LOADNUM": {
                float value = Integer.parseInt(tokens[1]);
                return new LoadNumberInstruction(value);
            }
            case "STORE": {
                int registerNumber = Integer.parseInt(tokens[1]);
                return new StoreInstruction(registerNumber);
            }
            case "JUMPNEG": {
                int programmSpeicherAddr = Integer.parseInt(tokens[1]);
                return new JumpNegInstruction(programmSpeicherAddr);
            }
            case "JUMPPOS": {
                int programmSpeicherAddr = Integer.parseInt(tokens[1]);
                return new JumpPosInstruction(programmSpeicherAddr);
            }
            case "JUMPNULL": {
                int programmSpeicherAddr = Integer.parseInt(tokens[1]);
                return new JumpNullInstruction(programmSpeicherAddr);
            }
            case "JUMP": {
                int programmSpeicherAddr = Integer.parseInt(tokens[1]);
                return new JumpInstruction(programmSpeicherAddr);
            }
            case "ADD": {
                int registryNumber = Integer.parseInt(tokens[1]);
                return new AddInstruction(registryNumber);
            }
            case "ADDNUM": {
                int value = Integer.parseInt(tokens[1]);
                return new AddNumInstruction(value);
            }
            case "SUB": {
                int value = Integer.parseInt(tokens[1]);
                return new SubInstruction(value);
            }
            case "REMOVE": {
                int registryNumber = Integer.parseInt(tokens[1]);
                return new RemoveInstruction(registryNumber);
            }
            default:
                return null;
        }
    }

    private static void printTokens(String[] tokens) {
        if (tokens.length == 2) {
            System.out.println("parsing: " + tokens[0] + "-" + tokens[1]);
        } else {
            System.out.println("parsing: " + tokens[0]);
        }
    }
}
