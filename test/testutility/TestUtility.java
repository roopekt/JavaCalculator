package testutility;

import com.calculator.solver.Lexeme;
import com.calculator.solver.LexemeParsing;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestUtility {

    public static void assertGetLexemes(List<Lexeme> expected, String expression) {
        List<Lexeme> actual = LexemeParsing.getLexemes(expression);
        if (!expected.equals(actual)) {
            String message = String.format("""
                    lexemes extracted incorrectly
                    Expression :\"%s\"
                    Expected   :%s
                    Actual     :%s\
                    """, expression, expected, actual);
            throw new AssertionError(message);
        }
    }

    @Test
    public void assertGetLexemes_no_unnecessary_fail_on_2_plus_2() {
        String expression = "2+2";
        List<Lexeme> expectedLexemes = Arrays.asList(
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL),
                new Lexeme("+", Lexeme.LexemeType.FUNCTION),
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL)
        );
        assertGetLexemes(expectedLexemes, expression);
    }

    @Test(expected = AssertionError.class)
    public void assertGetLexemes_fails_on_unexpected_output() {
        String expression = "2+2";
        List<Lexeme> expectedLexemes = Arrays.asList(
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL),
                new Lexeme("incorrect", Lexeme.LexemeType.FUNCTION),
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL)
        );
        assertGetLexemes(expectedLexemes, expression);
    }
}
