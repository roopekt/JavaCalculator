package com.calculator.solver.solvertest;

import com.calculator.solver.Lexeme;
import com.calculator.solver.ParenthesislessSolver;
import com.calculator.solver.Solver;
import com.calculator.solver.exceptions.MathException;
import com.calculator.solver.exceptions.SyntaxException;
import com.calculator.solver.exceptions.syntax.AdjacentFunctionsException;
import com.calculator.solver.exceptions.syntax.AdjacentNumericalsException;
import com.calculator.solver.exceptions.syntax.EmptyExpressionException;
import com.calculator.solver.exceptions.syntax.UnrecognisedFunctionException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.testutility.TestUtility.assertEvaluateExpressionThrowsAndError;
import static org.junit.Assert.assertThrows;

public class SyntaxErrorTest {

    @Test
    public void adjacent_functions_throw_an_error() {
        assertEvaluateExpressionThrowsAndError(AdjacentFunctionsException.class, "* *");
        assertEvaluateExpressionThrowsAndError(AdjacentFunctionsException.class, "1+ -2");
    }

    @Test(expected = AdjacentNumericalsException.class)
    public void adjacent_numericals_throw_an_error() throws MathException, SyntaxException {
        List<Lexeme> expression = Arrays.asList(
                new Lexeme("1", Lexeme.LexemeType.NUMBERLITERAL),
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL)
        );
        ParenthesislessSolver.evaluateParethesislessExpression(expression);
    }

    @Test(expected = EmptyExpressionException.class)
    public void empty_expression_throws_an_error() throws MathException, SyntaxException {
        List<Lexeme> emptyExpression = List.of();
        ParenthesislessSolver.evaluateParethesislessExpression(emptyExpression);
    }

    @Test
    public void wrong_function_symbol_throws_an_error() {
        assertEvaluateExpressionThrowsAndError(UnrecognisedFunctionException.class, "wrongSymbol");
    }

    @Test
    public void wrong_function_argument_signature_throws_an_error() {
        assertEvaluateExpressionThrowsAndError(UnrecognisedFunctionException.class, "1+");
        assertEvaluateExpressionThrowsAndError(UnrecognisedFunctionException.class, "*123");
    }
}
