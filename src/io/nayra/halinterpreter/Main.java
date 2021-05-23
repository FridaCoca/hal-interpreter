package io.nayra.halinterpreter;

import io.nayra.halinterpreter.instructions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;

public class Main {
    static boolean debugMode = true;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        debugMode = Arrays.asList(args).contains("-d");
        String scriptPath = findScriptPath(args);
        // find .config file and run HalOs
        HalOs os = new HalOs();
        os.run();
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }

    private static String findScriptPath(String[] args) {
        for (String a : args) {
            if (new File(a).exists()) {
                return a;
            }
        }
        return null;
    }
}
