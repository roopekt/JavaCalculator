package com.calculator.solver.mathfunctions.mathfunctionstest;

import com.calculator.solver.mathfunctions.MathFunctions;
import com.calculator.solver.mathfunctions.SyntaxDesc;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MathFunctionsOrderTest {

    @Test
    public void plus_comes_before_multiplication_and_division() {
        assertEvaluationOrder(MathFunctionsTest.plusDesc, MathFunctionsTest.multiplyDesc);
        assertEvaluationOrder(MathFunctionsTest.plusDesc, MathFunctionsTest.divideDesc);
    }

    @Test
    public void negate_comes_before_multiplication_and_division() {
        assertEvaluationOrder(MathFunctionsTest.negateDesc, MathFunctionsTest.multiplyDesc);
        assertEvaluationOrder(MathFunctionsTest.negateDesc, MathFunctionsTest.divideDesc);
    }

    @Test
    public void multiplication_comes_before_addition_and_subtraction() {
        assertEvaluationOrder(MathFunctionsTest.multiplyDesc, MathFunctionsTest.addDesc);
        assertEvaluationOrder(MathFunctionsTest.multiplyDesc, MathFunctionsTest.subtractDesc);
    }

    @Test
    public void division_comes_before_addition_and_subtraction() {
        assertEvaluationOrder(MathFunctionsTest.divideDesc, MathFunctionsTest.addDesc);
        assertEvaluationOrder(MathFunctionsTest.divideDesc, MathFunctionsTest.subtractDesc);
    }

    private void assertEvaluationOrder(SyntaxDesc shouldComeBefore, SyntaxDesc shouldComeAfter) {
        String errorMessage = String.format("%s should come before %s", shouldComeBefore, shouldComeAfter);
        assertTrue(errorMessage, MathFunctions.getIndexOfMathFunction(shouldComeBefore) <
                MathFunctions.getIndexOfMathFunction(shouldComeAfter));
    }
}
