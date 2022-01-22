package com.calculator.solver;

import java.util.Objects;

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

    public static boolean isEmpty(Lexeme lexeme) {
        return lexeme == null || lexeme.textValue == null || lexeme.textValue.equals("");
    }

    @Override
    public String toString() {
        return String.format("Lexeme(\"%s\", %s)", textValue, type);
    }

    @Override
    public int hashCode(){
        return textValue.hashCode() ^ type.hashCode();
    }

    @Override
    public boolean equals(Object other) {

        if (!(other instanceof Lexeme))
            return false;

        Lexeme otherAsLexeme = (Lexeme)other;
        return Objects.equals(this.textValue, otherAsLexeme.textValue) &&
                (this.type == otherAsLexeme.type);
    }
}