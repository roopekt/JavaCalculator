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
                new AdjacentFunctionsException(0, 2),
                () -> Solver.evaluateExpression("* *")
        );

        assertThrowsErrorWithCorrectData(
                new AdjacentFunctionsException(1, 3),
                () -> Solver.evaluateExpression("1+ -2")
        );
    }

    @Test
    public void adjacent_numericals_throw_correct_error() {
        Lexeme lexemeA = new Lexeme("1", Lexeme.LexemeType.NUMBERLITERAL);
        Lexeme lexemeB = new Lexeme("2", Lexeme.LexemeType.NUMBERLITERAL);

        List<Lexeme> expression = Arrays.asList(lexemeA, lexemeB);

        assertThrowsErrorWithCorrectData(
                new AdjacentNumericalsException(lexemeA, lexemeB),
                () -> ParenthesislessSolver.evaluateParethesislessExpression(expression)
        );
    }

    @Test
    public void empty_expression_throws_correct_error() {
        List<Lexeme> emptyExpression = List.of();

        assertThrowsErrorWithCorrectData(
                new EmptyExpressionException(),
                () -> ParenthesislessSolver.evaluateParethesislessExpression(emptyExpression)
        );
    }

    @Test
    public void wrong_function_symbol_throws_correct_error() {
        assertThrowsErrorWithCorrectData(
                new UnrecognisedFunctionException(new SyntaxDesc(false, "wrong", false), 0, 4),
                () -> Solver.evaluateExpression("wrong")
        );
    }

    @Test
    public void wrong_function_argument_signature_throws_correct_error() {
        assertThrowsErrorWithCorrectData(
                new UnrecognisedFunctionException(new SyntaxDesc(true, "+", false), 1, 1),
                () -> Solver.evaluateExpression("1+")
        );

        assertThrowsErrorWithCorrectData(
                new UnrecognisedFunctionException(new SyntaxDesc(false, "*", true), 0, 0),
                () -> Solver.evaluateExpression("*123")
        );
    }
}
