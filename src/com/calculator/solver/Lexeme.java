package com.calculator.solver;

public class Lexeme {

    enum LexemeType {
        NUMBERLITERAL,
        FUNCTION
    }

    public String textValue;
    public LexemeType type;

    public Lexeme(String textValue, LexemeType type) {
        this.textValue = textValue;
        this.type = type;
    }

    public Lexeme(String textValue) {
        this.textValue = textValue;
    }
}