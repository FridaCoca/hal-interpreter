package io.nayra.halinterpreter;

import io.nayra.halinterpreter.instructions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class HalProcessor extends Thread{
    public float[] memory = new float[100];
    public float[] registers = new float[16]; // makes string[] accessible from everywhere
    public float accu = 0;
    public int pc = 0;
    public float io0 = 0;
    public float io1 = 0;
    int neededRegister = -1;

    private final String scriptPath;

    int firstKey;
    int secondKey;
    int firstValue;
    int secondValue;

    public HashMap<Integer, Buffer> inputLinks = new HashMap<Integer, Buffer>();
    public HashMap<Integer, Buffer> outputLinks = new HashMap<Integer, Buffer>();

    public HalProcessor(String scriptPath) {
        System.out.println("ProcessorObject created - ProgramPath: " + scriptPath);

        this.scriptPath = scriptPath;


        //System.out.println("Links: " + firstKey + " > " + inputLinks.get(firstKey).get());
        //System.out.println("Links: " + secondKey + " > " + inputLinks.get(secondKey).get());

        fillArrayWith(memory, 0);
        fillArrayWith(registers, 0);
    }

    /**
     * initializes all the elements of array with value
     * @param array array to be filled with the provided value
     * @param value used to fill the array
     */
    @SuppressWarnings("SameParameterValue")
    private static void fillArrayWith(float[] array, int value) {
        Arrays.fill(array, value);
    }

    public void run() {
        if (scriptPath != null) {
            ArrayList<String> scriptLines = extractLinesFromFile(scriptPath);
            interpret(scriptLines);
        } else {
            System.out.println("No valid script file found in args");
        }
    }

    public void addBufferForInChannel(int channel, Buffer b){
        inputLinks.put(channel, b);
    }

    public void addBufferForOutChannel(int channel, Buffer b){
        outputLinks.put(channel, b);
    }


    void printCommand(String[] tokens) {
        debugPrint("----------------------------------------");
        if (tokens.length == 3) {
            debugPrint(tokens[1] + " " + tokens[2]);
        } else {
            debugPrint(tokens[1]);
        }
    }

    private void interpret(ArrayList<String> script) {
        while (pc < script.size()) {
            Instruction o = parseInstruction(script.get(pc));
            Class c = o.getClass();
            if (o instanceof StopInstruction) break;
            printForDebug(c);
            if (o.getInstructionIndex() == pc) {
                //print("Compare worked!!");
                if (o != null) o.run(this);
            }
            printForDebug(c);
        }
    }

    ArrayList<String> extractLinesFromFile(String filename) {
        return HalOs.extractLinesFromFile(filename);
    }

    public void printForDebug(Class c) {
        debugPrint("ACCU: " + accu);
        if (neededRegister != -1) {
            debugPrint("REG" + neededRegister + ": " + registers[neededRegister]);
        }
        debugPrint("PC: " + pc);
        debugPrint("----------------------------------------");
    }

    Instruction parseInstruction(String instruction) {
        String[] tokens = instruction.split(" ");
        printCommand(tokens);
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

    public void debugPrint(String s) {
        if (Main.debugMode) System.out.println(s);
    }
}
