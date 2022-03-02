package com.calculator.solver.lexemeparsingtest;

import com.calculator.solver.Lexeme;
import com.calculator.solver.LexemeParsing;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.testutility.TestUtility.assertGetLexemes;

public class GetLexemesTest {

    @Test
    public void lexemes_are_extracted_correctly(){
        String expression = "2+2";
        List<Lexeme> expectedLexemes = Arrays.asList(
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL),
                new Lexeme("+", Lexeme.LexemeType.FUNCTION),
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL)
        );
        assertGetLexemes(expectedLexemes, expression);

        expression = " 2.2 * 98 ";
        expectedLexemes = Arrays.asList(
                new Lexeme("2.2", Lexeme.LexemeType.NUMBERLITERAL),
                new Lexeme("*", Lexeme.LexemeType.FUNCTION),
                new Lexeme("98", Lexeme.LexemeType.NUMBERLITERAL)
        );
        assertGetLexemes(expectedLexemes, expression);

        expression = "-1 ++ (2 * (3))";
        expectedLexemes = Arrays.asList(
                new Lexeme("-", Lexeme.LexemeType.FUNCTION),
                new Lexeme("1", Lexeme.LexemeType.NUMBERLITERAL),
                new Lexeme("++", Lexeme.LexemeType.FUNCTION),
                new Lexeme("(", Lexeme.LexemeType.PARENTHESIS),
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL),
                new Lexeme("*", Lexeme.LexemeType.FUNCTION),
                new Lexeme("(", Lexeme.LexemeType.PARENTHESIS),
                new Lexeme("3", Lexeme.LexemeType.NUMBERLITERAL),
                new Lexeme(")", Lexeme.LexemeType.PARENTHESIS),
                new Lexeme(")", Lexeme.LexemeType.PARENTHESIS)
        );
        assertGetLexemes(expectedLexemes, expression);

        expression = "23 000 * -.123";
        expectedLexemes = Arrays.asList(
                new Lexeme("23000", Lexeme.LexemeType.NUMBERLITERAL),
                new Lexeme("*", Lexeme.LexemeType.FUNCTION),
                new Lexeme("-", Lexeme.LexemeType.FUNCTION),
                new Lexeme(".123", Lexeme.LexemeType.NUMBERLITERAL)
        );
        assertGetLexemes(expectedLexemes, expression);

        expression = "   2   +   2   ";
        expectedLexemes = Arrays.asList(
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL),
                new Lexeme("+", Lexeme.LexemeType.FUNCTION),
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL)
        );
        assertGetLexemes(expectedLexemes, expression);
    }

    @Test
    public void empty_string_will_result_in_empty_lexeme_list() {
        List<Lexeme> emptyLexemeList = List.of();
        assertGetLexemes(emptyLexemeList, "");
    }
}