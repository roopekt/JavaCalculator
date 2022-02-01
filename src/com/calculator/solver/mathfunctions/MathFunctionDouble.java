package com.calculator.solver.mathfunctions;

import com.calculator.solver.exceptions.MathException;

/**
 * Interface used to evaluate MathFunctions with doubles.
 * @see MathFunction
 */
public interface MathFunctionDouble {
    double evaluate(double... params) throws MathException;
}