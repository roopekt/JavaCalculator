package com.calculator.solver;

import org.junit.Test;
import static com.testutility.TestUtility.assertEvaluateExpression;

public class SolverTest {

    @Test
    public void natural_numbers_without_spaces_are_recognised_correctly() {
        assertEvaluateExpression(2, "2");
        assertEvaluateExpression(123, "123");
    }

    @Test
    public void natural_numbers_with_spaces_are_recognised_correctly() {
        assertEvaluateExpression(12, "1 2");
        assertEvaluateExpression(3145, "31 45");
    }

    @Test
    public void decimal_numbers_are_recognised_correctly() {
        assertEvaluateExpression(1.2, "1.2");
        assertEvaluateExpression(12.34, "12.34");
        assertEvaluateExpression(12.34, " 1 2 . 3 4 ");
    }
}