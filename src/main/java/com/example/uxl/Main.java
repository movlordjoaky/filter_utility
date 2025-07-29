package com.example.uxl;

public class Main {
    public static void main(String[] args) {
        ArgumentParser argumentParser = new ArgumentParser();
        FileProcessor fileProcessor = new FileProcessor();
        InputParameters inputParameters = new InputParameters();

        argumentParser.parseArgs(args, inputParameters);
        fileProcessor.processInput(inputParameters);
    }
}
