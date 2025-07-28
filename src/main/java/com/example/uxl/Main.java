package com.example.uxl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArgumentParser argumentParser = new ArgumentParser();
        FileProcessor fileProcessor = new FileProcessor();
        ErrorHandler errorHandler = new ErrorHandler();
        InputParameters inputParameters = new InputParameters();

        argumentParser.parseArgs(args, inputParameters);
        fileProcessor.processInput(inputParameters);

        for (String file : inputParameters.inputFiles) {
            System.out.println(file);
        }
    }
}
