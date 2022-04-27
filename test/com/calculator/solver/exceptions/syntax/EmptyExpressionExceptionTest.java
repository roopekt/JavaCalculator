package com.calculator.solver.exceptions.syntax;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmptyExpressionExceptionTest {

    @Test
    public  void new_instance_has_problematic_character_indexes_at_0() {
        EmptyExpressionException exception = new EmptyExpressionException();
        assertEquals(0, exception.firstProblematicCharacterIndex);
        assertEquals(0, exception.lastProblematicCharacterIndex);
    }
}
