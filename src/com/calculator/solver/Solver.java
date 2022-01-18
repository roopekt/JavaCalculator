package com.calculator.solver;

public class Solver {
    public static double evaluateExpression(String expression) {
        return getValueOfNumberLiteral(expression);
    }

    public static double getValueOfNumberLiteral(String numberLiteral) {
        numberLiteral = numberLiteral.replace(" ", "");
        return Double.parseDouble(numberLiteral);
    }
}
