package com.calculator.solver.mathfunctions;

import com.calculator.solver.NumValue;
import com.calculator.solver.exceptions.MathException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathFunctionTest {

    private final MathFunction addFunc = new MathFunction(
        new SyntaxDesc(true, "+", true),
        p -> p[0] + p[1]
    );
    private final MathFunction negateFunc = new MathFunction(
        new SyntaxDesc(false, "-", true),
        p -> -p[0]
    );
    private final MathFunction incrementFunc = new MathFunction(
        new SyntaxDesc(true, "++", false),
        p -> p[0] + 1
    );

    @Test
    public void two_param_function_is_evaluated_correctly_with_doubles() throws MathException {
        var actual = addFunc.evaluate(new NumValue(3), new NumValue(2));
        assertEquals(new NumValue(5), actual);
    }

    @Test
    public void left_param_function_is_evaluated_correctly_with_doubles() throws MathException {
        var actual = incrementFunc.evaluate(new NumValue(2), null);
        assertEquals(new NumValue(3), actual);
    }

    @Test
    public void right_param_function_is_evaluated_correctly_with_doubles() throws MathException {
        var actual = negateFunc.evaluate(null, new NumValue(2));
        assertEquals(new NumValue(-2), actual);
    }

    @Test(expected = RuntimeException.class)
    public void error_is_thrown_when_evaluate_called_with_missing_arguments() throws MathException {
        addFunc.evaluate(new NumValue(1), null);
    }

    @Test(expected = RuntimeException.class)
    public void error_is_thrown_when_evaluate_called_with_unnecessary_arguments() throws MathException {
        negateFunc.evaluate(new NumValue(1), new NumValue(2));
    }
}