package io.nayra.halinterpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HalOs {
    final String configFile;

    public HalOs(String configFile) {
        System.out.println("HalOs Object created");
        this.configFile = configFile;
    }


    /**
     * Find, read and initialize OS with configFile
     */
    public void run() {
        ArrayList<String> InstructionLinesInConfig = extractLinesFromFile(configFile);
        int size = InstructionLinesInConfig.size();

        String firstStringInLine;
        String pathToHalProgram;

        for (int i = 1; i < size ; i++) {
            String instructionLine = InstructionLinesInConfig.get(i);
            String[] stringsInLine = instructionLine.trim().split("\\s+");
            pathToHalProgram = stringsInLine[1];

            firstStringInLine = stringsInLine[0];
            boolean firstStringIsInt = firstStringInLine.matches("\\d");
            if(! firstStringIsInt){
                break;
            }

            HalProcessor processor = new HalProcessor(pathToHalProgram);

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
}
