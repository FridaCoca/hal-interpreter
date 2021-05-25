package io.nayra.halinterpreter;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HalOs {
    final String configFile;

    public HalOs(String configFile) {
        System.out.println("HalOs Object created");
        this.configFile = configFile;
    }


    /**
     * Find, read and initialize OS with configFile
     */
    public void rxun() {
        ArrayList<String> InstructionLinesInConfig = extractLinesFromFile(configFile);
        int size = InstructionLinesInConfig.size();

        String firstStringInLine;
        String pathToHalProgram;
        int instructionIndex = 0;

        for (int i = 1; i < size; i++) {
            String instructionLine = InstructionLinesInConfig.get(i);
            String[] stringsInLine = instructionLine.trim().split("\\s+");
            pathToHalProgram = stringsInLine[1];

            firstStringInLine = stringsInLine[0];
            boolean firstStringIsInt = firstStringInLine.matches("\\d");
            if (!firstStringIsInt) {
                instructionIndex = i;
                break;
            }
            HalProcessor processor = new HalProcessor(pathToHalProgram, 1, 2, 3, 4);
        }


        // Prozessoren linken


        // Buffer aufrufen

    }


    public void run() {
        ArrayList<String> InstructionLinesInConfig = extractLinesFromFile(configFile);


        splitFile(InstructionLinesInConfig);

        String pathToHalProgram;
        int instructionIndex = 0;

        for (int i = 1; i < size; i++) {
            String instructionForProcessorCreation = scriptFirstPart.get(i);
            String instructionsForLinking = scriptSecondPart.get(i);

            String[] stringsInLine = instructionForProcessorCreation.trim().split("\\s+");
            pathToHalProgram = stringsInLine[1];

            stringsInLine = instructionsForLinking.trim().split("\\s+");
            int firstKey = stringsInLine[0].charAt(0);
            int secondKey = stringsInLine[0].charAt(2);
            int firstValue = stringsInLine[3].charAt(0);
            int secondValue = stringsInLine[3].charAt(2);

            HalProcessor processor = new HalProcessor(pathToHalProgram, firstKey, firstValue, secondKey, secondValue);
        }


        // Prozessoren linken


        // Buffer aufrufen

    }


    public static ArrayList<String> extractLinesFromFile(String filename) {
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

    ArrayList<String> scriptFirstPart = new ArrayList<>();
    ArrayList<String> scriptSecondPart = new ArrayList<>();


    public void splitFile(ArrayList<String> result) {
        int size = result.size();
        boolean signalForFirstPart = true;

        for (int i = 1; i < size; i++) {
            String instructionLine = result.get(i);

            // trim() -> eliminates spaces in front or end of a string
            // " lola " -> "lola"
            String[] stringsInLine = instructionLine.trim().split("\\s+"); // + -> 1 or more spaces
            String firstStringInLine = stringsInLine[0];

            //Nested Ifs (or nested Fors) is a signal that the current logic is too primitive

            if (signalForFirstPart) {
                if (firstStringInLine.equals("HAL")) {
                    signalForFirstPart = false;
                } else {
                    System.out.println("Line in Script 1: " + instructionLine);
                    scriptFirstPart.add(instructionLine);
                }
            } else {
                System.out.println("Line in Script 2: " + instructionLine);
                scriptSecondPart.add(instructionLine);
            }
        }
    }

}
