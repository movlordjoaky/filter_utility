package com.example.uxl;

import java.util.ArrayList;
import java.util.List;

public class InputParameters {
    public StatisticsMode statisticsMode;
    public OutputMode outputMode;
    public String outputPath;
    public String prefix;
    public List<String> inputFiles;

    public InputParameters() {
        this.statisticsMode = StatisticsMode.NONE;
        this.outputMode = OutputMode.REWRITE;
        this.outputPath = "";
        this.prefix = "";
        this.inputFiles = new ArrayList<>();
    }

    public enum StatisticsMode {
        FULL,
        SHORT,
        NONE
    }

    public enum OutputMode {
        REWRITE,
        ADD
    }
}
