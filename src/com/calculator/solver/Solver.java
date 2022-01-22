package com.calculator.solver;

public class Solver {
    public static NumValue evaluateExpression(String expression) {
        return getValueOfNumberLiteral(expression);
    }

    public static NumValue getValueOfNumberLiteral(String numberLiteral) {
        numberLiteral = numberLiteral.replace(" ", "");
        double value = Double.parseDouble(numberLiteral);
        return new NumValue(value);
    }
}