package com.calculator.solver.exceptions.syntax;

import org.junit.Test;

import static org.junit.Assert.*;

public class IncorrectNumberLiteralExceptionTest {

    @Test
    public void new_instance_remembers_given_numberLiteral() {
        var instance = new IncorrectNumberLiteralException("1..2");
        assertEquals("1..2", instance.numberLiteral);
    }
}