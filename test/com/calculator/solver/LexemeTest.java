package com.calculator.solver;

import org.junit.Test;

import static org.junit.Assert.*;

public class LexemeTest {

    @Test
    public void new_lexeme_remembers_textValue() {
        var newLexeme = new Lexeme("abc");
        assertEquals("abc", newLexeme.textValue);

        newLexeme = new Lexeme("123");
        assertEquals("123", newLexeme.textValue);
    }

    @Test
    public void new_lexeme_remembers_type() {
        var newLexeme = new Lexeme(Lexeme.LexemeType.NUMBERLITERAL);
        assertEquals(Lexeme.LexemeType.NUMBERLITERAL, newLexeme.type);

        newLexeme = new Lexeme(Lexeme.LexemeType.FUNCTION);
        assertEquals(Lexeme.LexemeType.FUNCTION, newLexeme.type);
    }

    @Test
    public void new_lexeme_remembers_textValue_and_type() {
        var newLexeme = new Lexeme("abc", Lexeme.LexemeType.NUMBERLITERAL);
        assertEquals("abc", newLexeme.textValue);
        assertEquals(Lexeme.LexemeType.NUMBERLITERAL, newLexeme.type);

        newLexeme = new Lexeme("123", Lexeme.LexemeType.FUNCTION);
        assertEquals("123", newLexeme.textValue);
        assertEquals(Lexeme.LexemeType.FUNCTION, newLexeme.type);
    }

    @Test
    public void new_lexeme_has_empty_string_as_textValue_if_not_given() {
        var newLexeme = new Lexeme(Lexeme.LexemeType.NUMBERLITERAL);
        assertEquals("", newLexeme.textValue);
    }

    @Test
    public void similar_lexemes_are_equal() {
        var lexeme1 = new Lexeme("abc", Lexeme.LexemeType.NUMBERLITERAL);
        var lexeme2 = new Lexeme("abc", Lexeme.LexemeType.NUMBERLITERAL);
        assertEquals(lexeme1, lexeme2);
        assertEquals(lexeme1.hashCode(), lexeme2.hashCode());

        var lexeme3 = new Lexeme("test", Lexeme.LexemeType.FUNCTION);
        var lexeme4 = new Lexeme("test", Lexeme.LexemeType.FUNCTION);
        assertEquals(lexeme3, lexeme4);
        assertEquals(lexeme3.hashCode(), lexeme4.hashCode());
    }

    @Test
    public void different_lexemes_are_not_equal() {
        var lexeme1 = new Lexeme("abc", Lexeme.LexemeType.NUMBERLITERAL);
        var lexeme2 = new Lexeme("123", Lexeme.LexemeType.NUMBERLITERAL);
        assertNotEquals(lexeme1, lexeme2);
        assertNotEquals(lexeme1.hashCode(), lexeme2.hashCode());

        var lexeme3 = new Lexeme("test", Lexeme.LexemeType.NUMBERLITERAL);
        var lexeme4 = new Lexeme("test", Lexeme.LexemeType.FUNCTION);
        assertNotEquals(lexeme3, lexeme4);
        assertNotEquals(lexeme3.hashCode(), lexeme4.hashCode());
    }

    @Test
    public void new_lexeme_is_not_equal_to_null() {
        assertNotEquals(new Lexeme(""), null);
    }

    @Test
    public void lexeme_is_not_equal_to_random_object() {
        assertNotEquals(new Lexeme("abc"), "random string object");
    }

    @Test
    public void can_compare_lexemes_with_null_as_textValue() {
        var lexeme1 = new Lexeme("");
        lexeme1.textValue = null;
        var lexeme2 = new Lexeme("");
        lexeme2.textValue = null;
        assertEquals(lexeme1, lexeme2);

        var lexeme3 = new Lexeme("");
        lexeme3.textValue = null;
        var lexeme4 = new Lexeme("");
        assertNotEquals(lexeme3, lexeme4);
        assertNotEquals(lexeme4, lexeme3);
    }

    @Test
    public void lexeme_equals_compares_textValue_by_value(){
        var lexeme1 = new Lexeme("abc");
        var lexeme2 = new Lexeme(new String("abc"));
        assertEquals(lexeme1, lexeme2);
    }

    @Test
    public void new_lexeme_has_value_null() {
        var lexeme = new Lexeme("abc");
        assertNull(lexeme.value);
    }
}