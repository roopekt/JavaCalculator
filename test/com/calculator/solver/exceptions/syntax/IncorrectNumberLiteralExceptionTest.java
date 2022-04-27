package com.calculator.solver.exceptions.syntax;

import org.junit.Test;

import static org.junit.Assert.*;

public class IncorrectNumberLiteralExceptionTest {

    @Test
    public void new_instance_remembers_given_values() {
        var instance = new IncorrectNumberLiteralException("1..2", 10, 13);
        assertEquals("1..2", instance.numberLiteral);
        assertEquals(10, instance.firstProblematicCharacterIndex);
        assertEquals(13, instance.lastProblematicCharacterIndex);

    }
}