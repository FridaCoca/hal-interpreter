package io.nayra.halinterpreter;

import java.util.Arrays;
import java.io.File;

public class Main {
    public static boolean debugMode = true;

    public static void main(String[] args) {
        System.out.println("Working Dir : " + System.getProperty("user.dir"));
        // DEBUG MODE
        args = new String[]{".config"}; //"-d", to debug

        //TODO: Loop start fuer alle Processoren im Vector
        long startTime = System.currentTimeMillis();
        debugMode = Arrays.asList(args).contains("-d");
        String configFile = findFile(args);
        HalOs os = new HalOs(configFile);
        os.run();
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }

    private static String findFile(String[] args) {
        for (String a : args) {
            if (new File(a).exists()) {
                return a;
            }
        }
        return null;
    }
}
