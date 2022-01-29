package com.calculator.solver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumValueTest {

    @Test
    public void new_NumValue_remembers_its_value() {
        var numValue = new NumValue(3.14);
        assertEquals(3.14, numValue.getDouble(), 0.001);
    }

    @Test
    public void two_NumValues_with_approximately_same_doubleValue_are_equal() {
        var numValueA = new NumValue(314);
        var numValueB = new NumValue(314 * 1e5 / 1e5);
        assertEquals(numValueA, numValueB);
    }
}
