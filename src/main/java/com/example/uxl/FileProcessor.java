package com.example.uxl;

import java.io.File;
import java.nio.file.*;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import com.example.uxl.TextFilter.TextType;


public class FileProcessor {
    TextFilter textFilter = new TextFilter();
    Set<TextType> fileCreated = new HashSet<>();

    public void processInput(InputParameters inputParameters) {
        for (String file : inputParameters.inputFiles) {
            try (Stream<String> lines = Files.lines(Paths.get(file))) {
                lines.forEach(line -> processLine(inputParameters, line));
            } catch (IOException e) {
                System.err.println("File reading error: " + file);
            }
        }
    }

    private void processLine(InputParameters inputParameters, String line) {
        if (!line.isBlank()) {
            TextType textType = textFilter.getTextType(line);
            String outputFile = inputParameters.prefix;

            switch (textType) {
                case INT -> outputFile += "integers.txt";
                case FLOAT -> outputFile += "floats.txt";
                case STRING -> outputFile += "strings.txt";
            }
            if (!inputParameters.outputPath.isBlank() && !inputParameters.outputPath.endsWith("/")) {
                inputParameters.outputPath += "/";
            }
            outputFile = inputParameters.outputPath + outputFile;
            try {
                outputLine(inputParameters, textType, outputFile, line);
            } catch (InvalidPathException e) {
                System.err.println("Invalid Path: " + outputFile);
            } catch (IOException e) {
                System.err.println("File writing error: " + outputFile);
            }
        }
    }

    private void outputLine(InputParameters inputParameters, TextType textType, String outputFile, String line)
            throws IOException {
        Set<StandardOpenOption> writeOptions = new HashSet<>();
        Path path = Paths.get(outputFile);
        if (fileCreated.contains(textType) ||
                !fileCreated.contains(textType) && Files.exists(path)
                        && inputParameters.outputMode == InputParameters.OutputMode.ADD) {
            writeOptions.add(StandardOpenOption.APPEND);
        } else {
            writeOptions.add(StandardOpenOption.CREATE);
            writeOptions.add(StandardOpenOption.TRUNCATE_EXISTING);
            writeOptions.add(StandardOpenOption.WRITE);
        }
        StandardOpenOption[] writeOptionsArray = writeOptions.toArray(new StandardOpenOption[0]);
        Files.write(path, (line + "\n").getBytes(), writeOptionsArray);
        fileCreated.add(textType);
    }
}
