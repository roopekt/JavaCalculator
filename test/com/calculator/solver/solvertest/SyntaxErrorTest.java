package com.calculator.solver.solvertest;

import com.calculator.solver.Lexeme;
import com.calculator.solver.ParenthesislessSolver;
import com.calculator.solver.Solver;
import com.calculator.solver.exceptions.MathException;
import com.calculator.solver.exceptions.SyntaxException;
import com.calculator.solver.exceptions.syntax.*;
import com.calculator.solver.mathfunctions.SyntaxDesc;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.testutility.TestUtility.assertThrowsErrorWithCorrectData;

public class SyntaxErrorTest {

    @Test
    public void adjacent_functions_throw_correct_error() {
        assertThrowsErrorWithCorrectData(
                new AdjacentFunctionsException(),
                () -> Solver.evaluateExpression("* *")
        );

        assertThrowsErrorWithCorrectData(
                new AdjacentFunctionsException(),
                () -> Solver.evaluateExpression("1+ -2")
        );
    }

    @Test
    public void adjacent_numericals_throw_correct_error() {
        List<Lexeme> expression = Arrays.asList(
                new Lexeme("1", Lexeme.LexemeType.NUMBERLITERAL),
                new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL)
        );
        assertThrowsErrorWithCorrectData(
                new AdjacentNumericalsException(),
                () -> ParenthesislessSolver.evaluateParethesislessExpression(expression)
        );
    }

    @Test(expected = EmptyExpressionException.class)
    public void empty_expression_throws_an_error() throws MathException, SyntaxException {
        List<Lexeme> emptyExpression = List.of();
        ParenthesislessSolver.evaluateParethesislessExpression(emptyExpression);
    }

    @Test
    public void wrong_function_symbol_throws_correct_error() {
        assertThrowsErrorWithCorrectData(
                new UnrecognisedFunctionException(new SyntaxDesc(false, "wrong", false)),
                () -> Solver.evaluateExpression("wrong")
        );
    }

    @Test
    public void wrong_function_argument_signature_throws_correct_error() {
        assertThrowsErrorWithCorrectData(
                new UnrecognisedFunctionException(new SyntaxDesc(true, "+", false)),
                () -> Solver.evaluateExpression("1+")
        );

        assertThrowsErrorWithCorrectData(
                new UnrecognisedFunctionException(new SyntaxDesc(false, "*", true)),
                () -> Solver.evaluateExpression("*123")
        );
    }
}
