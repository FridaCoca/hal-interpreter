package io.nayra.halinterpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class HalOs {
    final String configFile;
    private final Vector<HalProcessor> processors = new Vector<>();
    private final ArrayList<String> processorCreationInstructions = new ArrayList<>();
    private final ArrayList<String> linkingInstructions = new ArrayList<>();

    public HalOs(String configFile) {
        System.out.println("HalOs Object created");
        this.configFile = configFile;
    }

    public void run() {

        ArrayList<String> instructionLinesInConfig = extractLinesFromFile(configFile);
        splitFile(instructionLinesInConfig);

        for (String instruction : processorCreationInstructions) createProcessor(instruction);
        for (String instruction : linkingInstructions) linkProcessors(instruction);

        for(HalProcessor processor: processors){
            processor.start();
        }
    }

    private void linkProcessors(String instructionForLinking) {
        String[] stringsInLine = instructionForLinking.trim().split("\\s+");

        //0:3 > 1:2
        int outProcessor = Character.getNumericValue(stringsInLine[0].charAt(0));
        int outChannel = Character.getNumericValue(stringsInLine[0].charAt(2));
        int inProcessor = Character.getNumericValue(stringsInLine[2].charAt(0));
        int inChannel = Character.getNumericValue(stringsInLine[2].charAt(2));

        Buffer b = new Buffer();
        processors.get(outProcessor).addBufferForOutChannel(outChannel, b);
        processors.get(inProcessor).addBufferForInChannel(inChannel, b);
    }

    private void createProcessor(String instructionForProcessorCreation) {
        String[] stringsInLine = getStrings(instructionForProcessorCreation);
        String pathToHalProgram = stringsInLine[1];
        processors.add(new HalProcessor(pathToHalProgram));
    }

    private String[] getStrings(String instructionForProcessorCreation) {
        return instructionForProcessorCreation.trim().split("\\s+");
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

    public void splitFile(ArrayList<String> instructionLinesInConfig) {
        int size = instructionLinesInConfig.size();
        boolean signalForFirstPart = true;

        for (int i = 1; i < size; i++) {
            String instructionLine = instructionLinesInConfig.get(i);

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
                    processorCreationInstructions.add(instructionLine);
                }
            } else {
                System.out.println("Line in Script 2: " + instructionLine);
                linkingInstructions.add(instructionLine);
            }
        }
    }

}
