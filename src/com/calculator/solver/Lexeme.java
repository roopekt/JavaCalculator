package com.calculator.solver;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A single lexeme.
 * Holds data like the text it represents, type (e.g. number literal or function) and numerical value if it has been calculated.
 */
@ToString
@EqualsAndHashCode
public class Lexeme {

    public enum LexemeType {
        NUMBERLITERAL,
        FUNCTION,
        PARENTHESIS
    }

    public String textValue;
    public LexemeType type;
    public NumValue value;

    public Lexeme(String textValue, LexemeType type) {
        this.textValue = textValue;
        this.type = type;
    }

    public Lexeme(String textValue) {
        this.textValue = textValue;
    }

    public Lexeme(LexemeType type){
        this.textValue = "";
        this.type = type;
    }

    public Lexeme(NumValue value) {
        textValue = "";
        type = LexemeType.NUMBERLITERAL;
        this.value = value;
    }
}