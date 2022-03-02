package com.calculator.solver;

import java.util.List;
import java.util.ArrayList;

import static java.lang.Character.isDigit;

/**
 * Static methods for splitting an expression into lexemes, mainly getLexemes.
 * @see Lexeme
 * @see #getLexemes
 */
public class LexemeParsing {

    public static List<Lexeme> getLexemes(String expression) {
        List<Lexeme> lexemes = new ArrayList<Lexeme>();

        Lexeme newLexeme = null;
        Lexeme.LexemeType previousNonSpaceCharType = null;
        for (int i = 0; i < expression.length(); i++) {

            boolean previousCharWasSpace = i > 0 && expression.charAt(i - 1) == ' ';
            char newCharacter = expression.charAt(i);
            var newCharType = getCharLexemeType(newCharacter);

            if(newCharacter == ' ')
                continue;

            if (newLexeme == null) {
                newLexeme = new Lexeme("", newCharType);
            }
            else if (shouldBeInDifferentLexeme(newCharacter, previousNonSpaceCharType, previousCharWasSpace)) {
                addLexemeIfNotEmpty(newLexeme, lexemes);
                newLexeme = new Lexeme("", newCharType);
            }

            newLexeme.textValue += newCharacter;

            previousNonSpaceCharType = newCharType;
        }

        addLexemeIfNotEmpty(newLexeme, lexemes);

        return lexemes;
    }

    public static Lexeme.LexemeType getCharLexemeType(char character){
        if (isDigit(character) || character == '.')
            return Lexeme.LexemeType.NUMBERLITERAL;
        else if (character == '(' || character == ')')
            return Lexeme.LexemeType.PARENTHESIS;
        else if (character == ' ')
            return null;
        else
            return Lexeme.LexemeType.FUNCTION;
    }

    private static void addLexemeIfNotEmpty(Lexeme newLexeme, List<Lexeme> lexemeList) {
        if (newLexeme != null && !newLexeme.textValue.equals(""))
            lexemeList.add(newLexeme);
    }

    private static boolean shouldBeInDifferentLexeme(char newChar, Lexeme.LexemeType previousNonSpaceCharType, boolean previousCharWasSpace) {
        var newCharType = getCharLexemeType(newChar);

        if (previousNonSpaceCharType != newCharType)
            return true;
        if (newCharType == Lexeme.LexemeType.PARENTHESIS)
            return true;
        if (newCharType == Lexeme.LexemeType.FUNCTION && previousCharWasSpace)
            return true;

        return false;
    }
}