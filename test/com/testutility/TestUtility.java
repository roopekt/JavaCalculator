package com.testutility;

import com.calculator.solver.Lexeme;
import com.calculator.solver.LexemeParsing;
import com.calculator.solver.Solver;
import com.calculator.solver.exceptions.UserException;

import java.util.List;
import java.lang.Math;
import java.util.Objects;

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

    public interface ThrowingRunnable {
        void operation() throws Throwable;
    }

    public static void assertThrowsErrorWithCorrectData(Throwable expectedThrowable, ThrowingRunnable throwingRunnable) {
        Throwable actualThrowable = null;
        try {
            throwingRunnable.operation();
        }
        catch (Throwable throwable) {
            actualThrowable = throwable;
        }

        if (!Objects.equals(expectedThrowable, actualThrowable)) {
            String message = """
                    Correct throwable was not thrown
                    Expected: %s
                    Actual: %s
                    """.formatted(expectedThrowable, actualThrowable);
            throw new AssertionError(message);
        }
    }

    public static void assertDoublesEqual(double expected, double actual) {
        assertEquals(expected, actual, 0.001);
    }
}
