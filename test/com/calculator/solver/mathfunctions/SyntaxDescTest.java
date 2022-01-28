package com.calculator.solver.mathfunctions;

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
    public void instance_constructed_is_equal_to_original() {
        var instanceA = new SyntaxDesc(true, "f", false);
        var instanceB = new SyntaxDesc(instanceA);
        assertEquals(instanceA, instanceB);
    }
}