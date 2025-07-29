package com.example.uxl;

public class TextFilter {
    public TextType getTextType(String text) {
        TextType textType;
        if (isInt(text)) {
            textType = TextType.INT;
        } else if (isFloat(text)) {
            textType = TextType.FLOAT;
        } else {
            textType = TextType.STRING;
        }
        return textType;
    }

    private boolean isInt(String text) {
        return text.strip().matches("-?\\d+");
    }

    private boolean isFloat(String text) {
        return text.strip().matches("-?\\d\\.\\d+([Ee]-?\\d+)?");
    }

    public enum TextType {
        INT, FLOAT, STRING
    }
}
