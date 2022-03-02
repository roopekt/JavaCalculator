package com.calculator.solver.mathfunctions.mathfunctionstest;

import com.calculator.solver.NumValue;
import com.calculator.solver.exceptions.MathException;
import com.calculator.solver.exceptions.math.FailedExponentiationException;
import com.calculator.solver.exceptions.math.ZeroDivisionException;
import org.junit.Test;

public class MathFunctionsDoubleTest {

    @Test
    public void _2_plus_3_is_5() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.addDesc,
                new NumValue(5),
                new NumValue(2),
                new NumValue(3)
        );
    }

    @Test
    public void _0_plus_0_is_0() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.addDesc,
                new NumValue(0),
                new NumValue(0),
                new NumValue(0)
        );
    }

    @Test
    public void _2_minus_4_is_minus_2() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.subtractDesc,
                new NumValue(-2),
                new NumValue(2),
                new NumValue(4)
        );
    }

    @Test
    public void _2_times_3_is_6_with_doubles() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.multiplyDesc,
                new NumValue(6),
                new NumValue(2),
                new NumValue(3)
        );
    }

    @Test
    public void _0_times_0_is_0_with_doubles() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.multiplyDesc,
                new NumValue(0),
                new NumValue(0),
                new NumValue(0)
        );
    }

    @Test
    public void _6_divided_by_2_is_3_with_doubles() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.divideDesc,
                new NumValue(3),
                new NumValue(6),
                new NumValue(2)
        );
    }

    @Test(expected = ZeroDivisionException.class)
    public void _2_divided_by_0_throws_an_error() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.divideDesc,
                null,
                new NumValue(2),
                new NumValue(0)
        );
    }

    @Test(expected = ZeroDivisionException.class)
    public void _0_divided_by_0_throws_an_error() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.divideDesc,
                null,
                new NumValue(0),
                new NumValue(0)
        );
    }

    @Test
    public void plus_2_is_2() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.plusDesc,
                new NumValue(2),
                null,
                new NumValue(2)
        );
    }

    @Test
    public void plus_minus_2_is_minus_2() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.plusDesc,
                new NumValue(-2),
                null,
                new NumValue(-2)
        );
    }

    @Test
    public void minus_2_is_minus_2() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.negateDesc,
                new NumValue(-2),
                null,
                new NumValue(2)
        );
    }

    @Test
    public void minus_minus_2_is_2() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.negateDesc,
                new NumValue(2),
                null,
                new NumValue(-2)
        );
    }

    @Test
    public void _2_raised_to_3_is_8() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.powerDesc,
                new NumValue(8),
                new NumValue(2),
                new NumValue(3)
        );
    }

    @Test
    public void half_raised_to_3_is_one_8th() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.powerDesc,
                new NumValue(1./8),
                new NumValue(1./2),
                new NumValue(3)
        );
    }

    @Test
    public void _4_raised_to_minus_half_is_half() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.powerDesc,
                new NumValue(1./2),
                new NumValue(4),
                new NumValue(-1./2)
        );
    }

    @Test
    public void minus_2_raised_to_3_is_minus_8() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.powerDesc,
                new NumValue(-8),
                new NumValue(-2),
                new NumValue(3)
        );
    }

    @Test
    public void minus_half_raised_to_2_is_one_4th() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.powerDesc,
                new NumValue(1./4),
                new NumValue(-1./2),
                new NumValue(2)
        );
    }

    @Test
    public void _2_raised_to_0_is_1() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.powerDesc,
                new NumValue(1),
                new NumValue(2),
                new NumValue(0)
        );
    }

    @Test
    public void minus_2_raised_to_0_is_1() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.powerDesc,
                new NumValue(1),
                new NumValue(-2),
                new NumValue(0)
        );
    }

    @Test
    public void _0_raised_to_2_is_0() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.powerDesc,
                new NumValue(0),
                new NumValue(0),
                new NumValue(2)
        );
    }

    @Test(expected = ZeroDivisionException.class)
    public void _0_raised_to_minus_1_throws_an_error() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.powerDesc,
                null,
                new NumValue(0),
                new NumValue(-1)
        );
    }

    @Test(expected = FailedExponentiationException.class)
    public void _0_raised_to_0_throws_an_error() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.powerDesc,
                null,
                new NumValue(0),
                new NumValue(0)
        );
    }

    @Test(expected = FailedExponentiationException.class)
    public void minus_2_raised_to_half_throws_an_error() throws MathException {
        MathFunctionsTest.assertFindAndEvaluateFunction(
                MathFunctionsTest.powerDesc,
                null,
                new NumValue(-2),
                new NumValue(1./2)
        );
    }
}
