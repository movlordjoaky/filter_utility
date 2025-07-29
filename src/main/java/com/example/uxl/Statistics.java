package com.example.uxl;

import com.example.uxl.TextFilter.TextType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.example.uxl.InputParameters.StatisticsMode;

public class Statistics {

    private final Map<TextType, Integer> linesAmount;
    private final NumberStats intStats, floatStats;
    private int totalLines;
    private Integer minStringLength, maxStringLength;

    public Statistics() {
        linesAmount = new EnumMap<>(TextType.class);
        for (TextType textType : TextType.values()) {
            linesAmount.put(textType, 0);
        }
        intStats = new NumberStats();
        floatStats = new NumberStats();
        totalLines = 0;
    }

    public void update(String line, TextType textType, StatisticsMode statisticsMode) {
        if (statisticsMode == StatisticsMode.SHORT || statisticsMode == StatisticsMode.FULL) {
            linesAmount.put(textType, linesAmount.getOrDefault(textType, 0) + 1);
            totalLines++;
        }
        if (statisticsMode == StatisticsMode.FULL) {
            if (textType == TextType.INT) {
                updateNumberStats(line, intStats);
            } else if (textType == TextType.FLOAT) {
                updateNumberStats(line, floatStats);
            } else {
                updateStringStats(line);
            }
        }
    }

    private void updateNumberStats(String line, NumberStats numberStats) {
        BigDecimal currentNumber = new BigDecimal(line);
        numberStats.min = (numberStats.min == null) ? currentNumber : numberStats.min.min(currentNumber);
        numberStats.max = (numberStats.max == null) ? currentNumber : numberStats.max.max(currentNumber);
        numberStats.sum = (numberStats.sum == null) ? currentNumber : numberStats.sum.add(currentNumber);
    }

    private void updateStringStats(String line) {
        int currentLength = line.length();
        minStringLength = (minStringLength == null) ? currentLength : Math.min(minStringLength, currentLength);
        maxStringLength = (maxStringLength == null) ? currentLength : Math.max(maxStringLength, currentLength);
    }

    public void print(StatisticsMode statisticsMode) {
        if (statisticsMode == StatisticsMode.SHORT) {
            linesAmount.forEach((textType, value) -> {
                System.out.println(textType.name() + " lines: " + value);
            });
            System.out.println("TOTAL lines: " + totalLines);
        } else if (statisticsMode == StatisticsMode.FULL) {
            printNumberStats(TextType.INT, intStats, linesAmount);
            printNumberStats(TextType.FLOAT, floatStats, linesAmount);

            System.out.println("STRING lines: " + linesAmount.get(TextType.STRING));
            System.out.println("MIN_STRING length: " + minStringLength);
            System.out.println("MIN_STRING length: " + maxStringLength);
            System.out.println();

            System.out.println("TOTAL lines: " + totalLines);
        }
    }

    private void printNumberStats(TextType textType, NumberStats numberStats,
                                  Map<TextType, Integer> linesAmount) {
        int count = linesAmount.get(textType);
        if (count != 0 && numberStats.sum != null) {
            numberStats.average = numberStats.sum.divide(BigDecimal.valueOf(count), 10, RoundingMode.HALF_UP);
        }
        System.out.println(textType + " lines: " + count);
        System.out.println(textType + "_MIN: " + finalFormat(numberStats.min));
        System.out.println(textType + "_MAX: " + finalFormat(numberStats.max));
        System.out.println(textType + "_SUM: " + finalFormat(numberStats.sum));
        System.out.println(textType + "_AVERAGE: " + finalFormat(numberStats.average));
        System.out.println();
    }

    private String finalFormat(BigDecimal value) {
        return value.setScale(10, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    private static class NumberStats {
        BigDecimal min;
        BigDecimal max;
        BigDecimal sum;
        BigDecimal average;
    }
}

