package com.calculator.solver.mathfunctions;

import com.calculator.solver.NumValue;
import org.junit.Test;

import static org.junit.Assert.*;

public class SyntaxDescTest {

    @Test
    public void similar_instances_are_equal() {
        var instanceA = new SyntaxDesc(true, "f", false);
        var instanceB = new SyntaxDesc(true, "f", false);
        assertEquals(instanceA, instanceB);
    }

    @Test
    public void different_instances_are_not_equal() {
        var instanceA = new SyntaxDesc(true, "f", false);
        var instanceB = new SyntaxDesc(true, "F", false);
        assertNotEquals(instanceA, instanceB);
    }

    @Test
    public void instance_constructed_through_copy_constructor_is_equal_to_original() {
        var instanceA = new SyntaxDesc(true, "f", false);
        var instanceB = new SyntaxDesc(instanceA);
        assertEquals(instanceA, instanceB);
    }

    @Test
    public void instance_constructed_from_params_and_function_symbol_is_correct() {
        var instanceA = new SyntaxDesc(new NumValue(0), "f", new NumValue(0));
        assertTrue(instanceA.leftArgPresent);
        assertEquals("f", instanceA.functionSymbol);
        assertTrue(instanceA.rightArgPresent);

        var instanceB = new SyntaxDesc(null, "F", null);
        assertFalse(instanceB.leftArgPresent);
        assertEquals("F", instanceB.functionSymbol);
        assertFalse(instanceB.rightArgPresent);

        var instanceC = new SyntaxDesc(new NumValue(0), "abc", null);
        assertTrue(instanceC.leftArgPresent);
        assertEquals("abc", instanceC.functionSymbol);
        assertFalse(instanceC.rightArgPresent);
    }
}