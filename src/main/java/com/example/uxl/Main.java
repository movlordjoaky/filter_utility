package com.example.uxl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String prefix = "";
        List<String> inputFiles = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            System.out.println("Arg: " + args[i]);
            switch (args[i]) {
                case "-s":
//                    statistics short
                    break;
                case "-f":
//                    statistics full
                    break;
                case "-o":
//                    path
                    break;
                case "-a":
//                    add mode
                    break;
                case "-p":
                    if (i + 1 < args.length) {
                        i++;
                        prefix = args[i];
                    }
                    break;
                default:
                    // inputFiles
                    inputFiles.add(args[i]);
                    break;
            }
        }
    }
}
