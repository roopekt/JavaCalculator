package com.calculator.solver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumValueTest {

    @Test
    public void new_NumValue_remembers_its_value() {
        var numValue = new NumValue(3.14);
        assertEquals(3.14, numValue.value, 0.001);
    }
}
