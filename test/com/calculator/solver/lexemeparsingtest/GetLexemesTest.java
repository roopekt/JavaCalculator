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
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL, 0, 0),
                new Lexeme("+", Lexeme.LexemeType.FUNCTION, 1, 1),
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL, 2, 2)
        );
        assertGetLexemes(expectedLexemes, expression);

        expression = "-1++(()(";
        expectedLexemes = Arrays.asList(
                new Lexeme("-", Lexeme.LexemeType.FUNCTION, 0, 0),
                new Lexeme("1", Lexeme.LexemeType.NUMBERLITERAL, 1, 1),
                new Lexeme("++", Lexeme.LexemeType.FUNCTION, 2, 3),
                new Lexeme("(", Lexeme.LexemeType.PARENTHESIS, 4, 4),
                new Lexeme("(", Lexeme.LexemeType.PARENTHESIS, 5, 5),
                new Lexeme(")", Lexeme.LexemeType.PARENTHESIS, 6, 6),
                new Lexeme("(", Lexeme.LexemeType.PARENTHESIS, 7, 7)
        );
        assertGetLexemes(expectedLexemes, expression);

        expression = "23 000 * -.123";
        expectedLexemes = Arrays.asList(
                new Lexeme("23000", Lexeme.LexemeType.NUMBERLITERAL, 0, 5),
                new Lexeme("*", Lexeme.LexemeType.FUNCTION, 7, 7),
                new Lexeme("-", Lexeme.LexemeType.FUNCTION, 9, 9),
                new Lexeme(".123", Lexeme.LexemeType.NUMBERLITERAL, 10, 13)
        );
        assertGetLexemes(expectedLexemes, expression);

        expression = "  2  +  2  ";
        expectedLexemes = Arrays.asList(
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL, 2, 2),
                new Lexeme("+", Lexeme.LexemeType.FUNCTION, 5, 5),
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL, 8, 8)
        );
        assertGetLexemes(expectedLexemes, expression);
    }

    @Test
    public void empty_string_will_result_in_empty_lexeme_list() {
        List<Lexeme> emptyLexemeList = List.of();
        assertGetLexemes(emptyLexemeList, "");
    }
}
