package com.testutility;

import com.calculator.solver.Lexeme;
import com.calculator.solver.Solver;
import com.calculator.solver.exceptions.UserException;
import com.calculator.solver.exceptions.math.ZeroDivisionException;
import com.calculator.solver.exceptions.syntax.IncorrectNumberLiteralException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.testutility.TestUtility.*;

public class TestUtilityTest {

    @Test
    public void assertGetLexemes_no_unnecessary_fail_on_2_plus_2() {
        String expression = "2+2";
        List<Lexeme> expectedLexemes = Arrays.asList(
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL, 0, 0),
                new Lexeme("+", Lexeme.LexemeType.FUNCTION, 1, 1),
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL, 2, 2)
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
    public void assertThrowsErrorWithCorrectData_passes_on_correct_error() {
        assertThrowsErrorWithCorrectData(
                new IncorrectNumberLiteralException(".", 0, 0),
                () -> Solver.evaluateExpression(".")
        );
    }

    @Test(expected = AssertionError.class)
    public void assertThrowsErrorWithCorrectData_fails_on_no_error() {
        assertThrowsErrorWithCorrectData(
                new IncorrectNumberLiteralException(".", 0, 0),
                () -> Solver.evaluateExpression("1")
        );
    }

    @Test(expected = AssertionError.class)
    public void assertThrowsErrorWithCorrectData_fails_on_wrong_type_of_error() {
        assertThrowsErrorWithCorrectData(
                new ZeroDivisionException(0, 0),
                () -> Solver.evaluateExpression(".")
        );
    }

    @Test(expected = AssertionError.class)
    public void assertThrowsErrorWithCorrectData_fails_on_error_with_wrong_data() {
        assertThrowsErrorWithCorrectData(
                new IncorrectNumberLiteralException("wrong data", -1, -1),
                () -> Solver.evaluateExpression(".")
        );
    }
}
