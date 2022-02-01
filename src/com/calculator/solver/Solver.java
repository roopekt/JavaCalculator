package com.calculator.solver;

import com.calculator.solver.exceptions.MathException;
import com.calculator.solver.exceptions.SyntaxException;
import com.calculator.solver.exceptions.syntax.IncorrectNumberLiteralException;

/**
 * The top level functionality for evaluating an expression.
 * @see #evaluateExpression(String expresion) 
 */
public class Solver {
    public static NumValue evaluateExpression(String expression) throws MathException, SyntaxException {
        return getValueOfNumberLiteral(expression);
    }

    private static NumValue getValueOfNumberLiteral(String numberLiteral) throws IncorrectNumberLiteralException {

        double value;
        try {
            value = Double.parseDouble(numberLiteral.replace(" ", ""));
        }
        catch (NumberFormatException exception) {
            throw new IncorrectNumberLiteralException(numberLiteral);
        }

        return new NumValue(value);
    }
}