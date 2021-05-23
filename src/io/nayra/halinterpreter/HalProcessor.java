package io.nayra.halinterpreter;

import io.nayra.halinterpreter.instructions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HalProcessor {
    public float[] memory = new float[100];
    public float[] registers = new float[16]; // makes string[] accessible from everywhere
    public float accu = 0;
    public int pc = 0;
    float io0 = 0;
    float io1 = 0;
    int neededRegister = -1;
    private final String scriptPath;

    public HalProcessor(String scriptPath) {
        this.scriptPath = scriptPath;
        fillArrayWith(memory, 0);
        fillArrayWith(registers, 0);
    }

    /**
     * initializes all the elements of array with value
     *
     * @param array array to be filled with the provided value
     * @param value used to fill the array
     */
    @SuppressWarnings("SameParameterValue")
    private static void fillArrayWith(float[] array, int value) {
        Arrays.fill(array, value);
    }

    void run() {
        if (scriptPath != null) {
            ArrayList<String> scriptLines = extractLinesFromFile(scriptPath);
            interpret(scriptLines);
        } else {
            System.out.println("No valid script file found in args");
        }
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

    private void printForDebug(Class c) {
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

    void debugPrint(String s) {
        if (Main.debugMode) System.out.println(s);
    }
}
