package com.example.uxl;

import java.util.Arrays;
import java.util.Iterator;

public class ArgumentParser {
    public void parseArgs(String[] args, InputParameters inputParameters) {
        Iterator<String> argsIterator = Arrays.asList(args).iterator();
        while (argsIterator.hasNext()) {
            parseArg(argsIterator, inputParameters);
        }
    }

    private void parseArg(Iterator<String> iterator, InputParameters inputParameters) {
        String arg = iterator.next();
        switch (arg) {
            case "-o" -> {
                if (iterator.hasNext()) {
                    inputParameters.outputPath = iterator.next();
                } else System.err.println("Path after -o is empty");
            }
            case "-p" -> {
                if (iterator.hasNext()) {
                    inputParameters.prefix = iterator.next();
                } else System.err.println("Prefix after -p is empty");
            }
            case "-a" -> inputParameters.outputMode = InputParameters.OutputMode.ADD;
            case "-s" -> {
                if (inputParameters.statisticsMode != InputParameters.StatisticsMode.NONE) {
                    inputParameters.statisticsMode = InputParameters.StatisticsMode.NONE;
                    System.err.println("More than one statistics flags. Set to NONE.");
                } else {
                    inputParameters.statisticsMode = InputParameters.StatisticsMode.SHORT;
                }
            }
            case "-f" -> {
                if (inputParameters.statisticsMode != InputParameters.StatisticsMode.NONE) {
                    inputParameters.statisticsMode = InputParameters.StatisticsMode.NONE;
                    System.err.println("More than one statistics flags. Set to NONE.");
                } else {
                    inputParameters.statisticsMode = InputParameters.StatisticsMode.FULL;
                }
            }
            default -> {
                if (arg.startsWith("-")) {
                    System.err.println("Unknown flag: " + arg);
                } else {
                    inputParameters.inputFiles.add(arg);
                }
            }
        }
    }
}