package com.calculator.solver.solvertest;

import com.calculator.solver.Solver;
import com.calculator.solver.exceptions.math.ZeroDivisionException;
import com.testutility.TestUtility;
import org.junit.Test;

public class MathErrorTest {

    @Test
    public void math_exceptions_report_correctly_where_the_problem_is() {
        var correctException = new ZeroDivisionException(20, 0);
        correctException.firstProblematicCharacterIndex = 4;
        correctException.lastProblematicCharacterIndex = 4;

        TestUtility.assertThrowsErrorWithCorrectData(
                correctException,
                () -> Solver.evaluateExpression("2*10/0.00")
        );
    }
}
