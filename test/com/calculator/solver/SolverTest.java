package com.calculator.solver;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTest {

    @Test
    public void _2_plus_2_is_4(){
        assertEvaluateExpression(4, "2+2");
    }

    private void assertEvaluateExpression(double expected, String expression){
        var actual = Solver.evaluateExpression(expression);
        assertEquals(expected, actual, .01);
    }
}