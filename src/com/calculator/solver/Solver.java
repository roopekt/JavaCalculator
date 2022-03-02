package com.calculator.solver;

import com.calculator.solver.exceptions.MathException;
import com.calculator.solver.exceptions.SyntaxException;

import java.util.List;

/**
 * The top level functionality for evaluating an expression.
 * @see #evaluateExpression(String expresion) 
 */
public class Solver {
    public static NumValue evaluateExpression(String expression) throws MathException, SyntaxException {
        List<Lexeme> lexemeList = LexemeParsing.getLexemes(expression);

        return ParenthesislessSolver.evaluateParethesislessExpression(lexemeList);
    }
}
