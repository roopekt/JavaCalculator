package com.calculator.solver.exceptions;

import com.calculator.solver.mathfunctions.SyntaxDesc;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WrongArgumentSignatureExceptionTest {

    SyntaxDesc someSignatureA;
    SyntaxDesc someSignatureB;

    @Before
    public void setup() {
        someSignatureA = new SyntaxDesc(true, "+", true);
        someSignatureB = new SyntaxDesc(false, "+", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructing_with_equal_expected_and_actual_argument_signature_throws_an_error() {
        new WrongArgumentSignatureException(someSignatureA, someSignatureA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructing_with_signatures_with_different_function_symbols_throw_an_error() {
        var signatureA = new SyntaxDesc(true, "+", true);
        var signatureB = new SyntaxDesc(true, "x", true);
        new WrongArgumentSignatureException(signatureA, signatureB);
    }

    @Test
    public void new_instance_remembers_given_expectedSignature() {
        var instance = new WrongArgumentSignatureException(someSignatureA, someSignatureB);
        assertEquals(someSignatureA, instance.expectedSignature);
    }

    @Test
    public void new_instance_remembers_given_actualSignature() {
        var instance = new WrongArgumentSignatureException(someSignatureA, someSignatureB);
        assertEquals(someSignatureB, instance.actualSignature);
    }

    @Test
    public void error_type_is_MISSING_LEFT_ARG_if_left_arg_missing() {
        assertSpecificErrorType(
                WrongArgumentSignatureException.SpecificErrorType.MISSING_LEFT_ARG,
                new SyntaxDesc(true, "+", true),
                new SyntaxDesc(false, "+", true)
        );
    }

    @Test
    public void error_type_is_UNEXPECTED_LEFT_ARG_if_unnecessary_left_arg() {
        assertSpecificErrorType(
                WrongArgumentSignatureException.SpecificErrorType.UNEXPECTED_LEFT_ARG,
                new SyntaxDesc(false, "+", true),
                new SyntaxDesc(true, "+", true)
        );
    }

    @Test
    public void error_type_is_MISSING_RIGHT_ARG_if_right_arg_missing() {
        assertSpecificErrorType(
                WrongArgumentSignatureException.SpecificErrorType.MISSING_RIGHT_ARG,
                new SyntaxDesc(true, "+", true),
                new SyntaxDesc(true, "+", false)
        );
    }

    @Test
    public void error_type_is_UNEXPECTED_RIGHT_ARG_if_unnecessary_right_arg() {
        assertSpecificErrorType(
                WrongArgumentSignatureException.SpecificErrorType.UNEXPECTED_RIGHT_ARG,
                new SyntaxDesc(true, "+", false),
                new SyntaxDesc(true, "+", true)
        );
    }

    private void assertSpecificErrorType(
            WrongArgumentSignatureException.SpecificErrorType expectedErrorType,
            SyntaxDesc expectedSignature,
            SyntaxDesc actualSignature) {
        var exceptionInstance = new WrongArgumentSignatureException(expectedSignature, actualSignature);
        assertEquals(expectedErrorType, exceptionInstance.getSpecificErrorType());
    }
}