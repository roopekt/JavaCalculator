package com.calculator.solver.exceptions.math;

import com.calculator.solver.NumValue;
import org.junit.Test;

import static com.testutility.TestUtility.assertDoublesEqual;
import static org.junit.Assert.*;

public class FailedExponentiationExceptionTest {

    @Test
    public void remembers_given_values_when_constructed_with_doubles() {
        var instance = new FailedExponentiationException(1, 2);
        assertDoublesEqual(1, instance.base.getDouble());
        assertDoublesEqual(2, instance.exponent.getDouble());
    }

    @Test
    public void remembers_given_values_when_constructed_with_NumValues() {
        var numValueA = new NumValue(1);
        var numValueB = new NumValue(2);
        var instance = new FailedExponentiationException(numValueA, numValueB);
        assertEquals(numValueA, instance.base);
        assertEquals(numValueB, instance.exponent);
    }
}