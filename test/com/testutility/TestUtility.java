package com.testutility;

import com.calculator.solver.Lexeme;
import com.calculator.solver.LexemeParsing;
import com.calculator.solver.Solver;
import com.calculator.solver.exceptions.UserException;
import com.calculator.solver.exceptions.math.ZeroDivisionException;
import com.calculator.solver.exceptions.syntax.IncorrectNumberLiteralException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.lang.Math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestUtility {

    public static void assertGetLexemes(List<Lexeme> expected, String expression) {
        List<Lexeme> actual = LexemeParsing.getLexemes(expression);
        if (!expected.equals(actual)) {
            String message = String.format("""
                    lexemes extracted incorrectly
                    Expression :"%s"
                    Expected   :%s
                    Actual     :%s\
                    """, expression, expected, actual);
            throw new AssertionError(message);
        }
    }

    public static void assertEvaluateExpression(double expected, String expression) throws UserException {
        double actual = Solver.evaluateExpression(expression).getDouble();
        boolean isCorrectResult = Math.abs(expected - actual) < 0.001;

        if (!isCorrectResult) {
            String message = String.format("""
                    expression evaluated incorrectly
                    Expression :"%s"
                    Expected   :%s
                    Actual     :%s\
                    """, expression, expected, actual);
            throw new AssertionError(message);
        }
    }

    public static <T extends Throwable> void assertEvaluateExpressionThrowsAndError(Class<T> expectedThrowable, String expression) {
        assertThrows(expectedThrowable, () -> Solver.evaluateExpression(expression));
    }

    public static void assertDoublesEqual(double expected, double actual) {
        assertEquals(expected, actual, 0.001);
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

    @Test
    public void assertEvaluateExpression_no_unnecessary_fail() throws UserException {
        assertEvaluateExpression(10, "10");
    }

    @Test(expected = AssertionError.class)
    public void assertEvaluateExpression_fails_on_unexpected_output() throws UserException {
        assertEvaluateExpression(5, "10");
    }

    @Test
    public void _2_is_2() {
        assertDoublesEqual(2, 2);
    }

    @Test(expected = AssertionError.class)
    public void _2_is_not_3() {
        assertDoublesEqual(2, 3);
    }

    @Test
    public void assertEvaluateExpressionThrowsAndError_passes_on_correct_error() {
        assertEvaluateExpressionThrowsAndError(IncorrectNumberLiteralException.class, ".");
    }

    @Test(expected = AssertionError.class)
    public void assertEvaluateExpressionThrowsAndError_fails_on_incorrect_error() {
        assertEvaluateExpressionThrowsAndError(ZeroDivisionException.class, ".");
    }

    @Test(expected = AssertionError.class)
    public void assertEvaluateExpressionThrowsAndError_fails_on_no_error() {
        assertEvaluateExpressionThrowsAndError(ZeroDivisionException.class, "1");
    }
}
