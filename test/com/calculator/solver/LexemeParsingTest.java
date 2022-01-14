package com.calculator.solver;

import org.junit.Test;
import static org.junit.Assert.*;

public class LexemeParsingTest {

    @Test
    public void two_is_part_of_number_literal() {
        assertEquals(Lexeme.LexemeType.NUMBERLITERAL, LexemeParsing.getCharLexemeType('2'));
    }

    @Test
    public void dot_is_part_of_number_literal() {
        assertEquals(Lexeme.LexemeType.NUMBERLITERAL, LexemeParsing.getCharLexemeType('.'));
    }

    @Test
    public void parenthesis_are_recognised_as_parenthesis() {
        assertEquals(Lexeme.LexemeType.PARENTHESIS, LexemeParsing.getCharLexemeType('('));
        assertEquals(Lexeme.LexemeType.PARENTHESIS, LexemeParsing.getCharLexemeType(')'));
    }

    @Test
    public void plus_is_a_function() {
        assertEquals(Lexeme.LexemeType.FUNCTION, LexemeParsing.getCharLexemeType('+'));
    }

    @Test
    public void lexeme_type_of_space_is_null() {
        assertEquals(null, LexemeParsing.getCharLexemeType(' '));
    }
}