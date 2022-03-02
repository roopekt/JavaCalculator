package com.calculator.solver.solvertest;

import com.calculator.solver.exceptions.UserException;
import org.junit.Test;

import static com.testutility.TestUtility.assertEvaluateExpression;

public class ParenthesislessTest {

    @Test
    public void single_subtraction_is_evaluated_correctly() throws UserException {
        assertEvaluateExpression(1, "3 - 2");
    }

    @Test
    public void single_negation_is_evaluated_correctly() throws UserException {
        assertEvaluateExpression(-2, "-2");
    }

    @Test
    public void division_is_evaluated_before_subtraction() throws UserException {
        assertEvaluateExpression(1, "4 / 2 - 1");
        assertEvaluateExpression(-1, "1 - 4 / 2");
    }

    @Test
    public void exponentiation_is_evaluated_before_division() throws UserException {
        assertEvaluateExpression(2, "4^2 / 8");
        assertEvaluateExpression(0.5, "8 / 4^2");
    }
}
