package com.calculator.solver.exceptions.math;

import com.calculator.solver.NumValue;
import com.calculator.solver.exceptions.MathException;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Thrown when a number is divided by 0.
 * Exponentiation may also throw this exception.
 * @see FailedExponentiationException
 */
@ToString
@AllArgsConstructor
public class ZeroDivisionException extends MathException {

    public final NumValue numerator;
    public final NumValue denominator;

    public ZeroDivisionException(double numerator, double denominator) {
        this.numerator = new NumValue(numerator);
        this.denominator = new NumValue(denominator);
    }

    @Override
    public String getMessageForUser() {
        return "Cannot divide by zero.";
    }
}
