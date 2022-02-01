package com.calculator.solver.exceptions;

import java.text.DecimalFormat;

/**
 * General utility methods for exceptions.
 */
public class ExceptionUtility {

    public static String doubleToString(double d) {
        var format = new DecimalFormat("0.00");
        return format.format(d);
    }
}
