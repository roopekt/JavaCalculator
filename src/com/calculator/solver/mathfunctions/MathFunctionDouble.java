package com.calculator.solver.mathfunctions;

import com.calculator.solver.exceptions.MathException;

public interface MathFunctionDouble {
    double evaluate(double... params) throws MathException;
}