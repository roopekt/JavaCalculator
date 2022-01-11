package com.calculator.solver;

import org.junit.Test;

import static org.junit.Assert.*;

public class LexemeTest {

    @Test
    public void new_lexeme_remembers_textValue(){
        var newLexeme = new Lexeme("abc");
        assertEquals("abc", newLexeme.textValue);
    }

    @Test
    public void new_lexeme_remembers_textValue_and_type(){
        var newLexeme = new Lexeme("abc", Lexeme.LexemeType.NUMBERLITERAL);
        assertEquals("abc", newLexeme.textValue);
        assertEquals(Lexeme.LexemeType.NUMBERLITERAL, newLexeme.type);

        newLexeme = new Lexeme("123", Lexeme.LexemeType.FUNCTION);
        assertEquals("123", newLexeme.textValue);
        assertEquals(Lexeme.LexemeType.FUNCTION, newLexeme.type);
    }
}