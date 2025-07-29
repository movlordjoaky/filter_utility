package com.example.uxl;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        ArgumentParser argumentParser = new ArgumentParser();
        FileProcessor fileProcessor = new FileProcessor();
        ErrorHandler errorHandler = new ErrorHandler();
        InputParameters inputParameters = new InputParameters();

        argumentParser.parseArgs(args, inputParameters);
        fileProcessor.processInput(inputParameters);

//        BigDecimal bigIntLike = new BigDecimal("1234.567890123456789012345678901234567890000000000000000000");
//        System.out.println(bigIntLike.setScale(10, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString());
//        for (String file : inputParameters.inputFiles) {
//            System.out.println(file);
//        }
    }
}
