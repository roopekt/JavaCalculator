package com.calculator.solver.exceptions.math;

import com.calculator.solver.NumValue;
import com.calculator.solver.exceptions.ExceptionUtility;
import com.calculator.solver.exceptions.MathException;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Thrown when a number cannot be raised to another.
 * A ZeroDivisionException may be thrown instead if the exponent is negative and base zero.
 * @see ZeroDivisionException
*/
@ToString
@AllArgsConstructor
public class FailedExponentiationException extends MathException {
    public final NumValue base;
    public final NumValue exponent;

    public FailedExponentiationException(double base, double exponent) {
        this.base = new NumValue(base);
        this.exponent = new NumValue(exponent);
    }

    @Override
    public String getMessageForUser() {
        return String.format(
                "Cannot raise %s to %s.",
                ExceptionUtility.doubleToString(base.getDouble()),
                ExceptionUtility.doubleToString(exponent.getDouble())
        );
    }
}
