package com.calculator.solver.solvertest;

import com.calculator.solver.Solver;
import com.calculator.solver.exceptions.UserException;
import com.calculator.solver.exceptions.syntax.IncorrectNumberLiteralException;
import com.calculator.solver.exceptions.syntax.UnrecognisedFunctionException;
import com.calculator.solver.mathfunctions.SyntaxDesc;
import org.junit.Test;
import static com.testutility.TestUtility.assertEvaluateExpression;
import static com.testutility.TestUtility.assertThrowsErrorWithCorrectData;

public class SingleNumberTest {

    @Test
    public void natural_numbers_without_spaces_are_recognised_correctly() throws UserException {
        assertEvaluateExpression(2, "2");
        assertEvaluateExpression(123, "123");
    }

    @Test
    public void natural_numbers_with_spaces_are_recognised_correctly() throws UserException {
        assertEvaluateExpression(12, "1 2");
        assertEvaluateExpression(3145, "31 45");
    }

    @Test
    public void decimal_numbers_are_recognised_correctly() throws UserException {
        assertEvaluateExpression(1.2, "1.2");
        assertEvaluateExpression(12.34, "12.34");
        assertEvaluateExpression(12.34, " 1 2 . 3 4 ");
    }

    @Test
    public void single_dot_cannot_be_evaluated() {
        assertThrowsErrorWithCorrectData(
                new IncorrectNumberLiteralException("."),
                () -> Solver.evaluateExpression(".")
        );
    }

    @Test
    public void number_literal_with_multiple_dots_cannot_be_evaluated() {
        assertThrowsErrorWithCorrectData(
                new IncorrectNumberLiteralException("1..2"),
                () -> Solver.evaluateExpression("1..2")
        );

        assertThrowsErrorWithCorrectData(
                new IncorrectNumberLiteralException("1.2.3"),
                () -> Solver.evaluateExpression("1.2.3")
        );
    }

    @Test
    public void number_literal_with_a_comma_cannot_be_evaluated() {
        assertThrowsErrorWithCorrectData(
                new UnrecognisedFunctionException(new SyntaxDesc(true, ",", true)),
                () -> Solver.evaluateExpression("1,2")
        );
    }
}