package com.calculator.solver;

import com.calculator.solver.exceptions.MathException;
import com.calculator.solver.exceptions.SyntaxException;
import com.calculator.solver.exceptions.syntax.*;
import com.calculator.solver.mathfunctions.MathFunction;
import com.calculator.solver.mathfunctions.MathFunctions;
import com.calculator.solver.mathfunctions.SyntaxDesc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

/**
 * Functionality to evaluate an expression that has no parenthesis.
 * @see #evaluateParethesislessExpression(List lexemeList)
 */
public class ParenthesislessSolver {

    public static NumValue evaluateParethesislessExpression(List<Lexeme> lexemeList) throws MathException, SyntaxException {
        List<Lexeme> unevaluatedList = new ArrayList<>(lexemeList);

        proactiveSyntaxErrorCheck(unevaluatedList);

        evaluateNumberLiteralsInLexemeList(lexemeList);

        for (MathFunction mathFunction : MathFunctions.getMathFunctionsInOrderOfExpectedEvaluation()) {
            evaluateAllInstancesOfFunctionInPlace(mathFunction, lexemeList);
        }

        if (lexemeList.size() != 1 || lexemeList.get(0).type != Lexeme.LexemeType.NUMBERLITERAL) {
            onFailedToEvaluate(unevaluatedList);
            return null;
        }

        return lexemeList.get(0).value;
    }

    private static void evaluateNumberLiteralsInLexemeList(List<Lexeme> lexemeList) throws IncorrectNumberLiteralException {
        for (Lexeme lexeme : lexemeList) {
            if (lexeme.type == Lexeme.LexemeType.NUMBERLITERAL) {
                double doubleValue = getDoubleValueOfNumberLiteral(lexeme.textValue);
                lexeme.value = new NumValue(doubleValue);
            }
        }
    }

    private static double getDoubleValueOfNumberLiteral(String numberLiteral) throws IncorrectNumberLiteralException {
        boolean nothingExtra = Pattern.compile("[0-9]*\\.?[0-9]*")//0 or more numbers, maybe a dot, 0 or more numbers
                .matcher(numberLiteral).matches();
        if (!nothingExtra)
            throw new IncorrectNumberLiteralException(numberLiteral);

        try {
            return Double.parseDouble(numberLiteral.replace(" ", ""));
        }
        catch (NumberFormatException exception) {
            throw new IncorrectNumberLiteralException(numberLiteral);
        }
    }

    private static void evaluateAllInstancesOfFunctionInPlace(MathFunction function, List<Lexeme> lexemeList) throws MathException {
        for (int i = 0; i < lexemeList.size(); i++) {
            NumValue leftArg = getLeftArg(i, lexemeList);
            NumValue rightArg = getRightArg(i, lexemeList);

            if (isSameFunctionSyntax(leftArg, lexemeList.get(i), rightArg, function.syntaxDesc)) {
                NumValue evaluationResult = function.evaluate(leftArg, rightArg);
                PlaceEvaluationResultIntoLexemeList(evaluationResult, function.syntaxDesc, i, lexemeList);
            }
        }
    }

    private static void PlaceEvaluationResultIntoLexemeList(NumValue result, SyntaxDesc syntaxDesc, int functionLexemeIndex, List<Lexeme> lexemeList) {
        lexemeList.set(functionLexemeIndex, new Lexeme(result));

        if (syntaxDesc.rightArgPresent)
            lexemeList.remove(functionLexemeIndex + 1);

        if (syntaxDesc.leftArgPresent)
            lexemeList.remove(functionLexemeIndex - 1);
    }

    private static NumValue getLeftArg(int functionLexemeIndex, List<Lexeme> lexemeList) {
        if (functionLexemeIndex == 0)
            return null;

        Lexeme paramLexeme = lexemeList.get(functionLexemeIndex - 1);

        if (paramLexeme.type != Lexeme.LexemeType.NUMBERLITERAL)
            return null;

        return paramLexeme.value;
    }

    private static NumValue getRightArg(int functionLexemeIndex, List<Lexeme> lexemeList) {
        if (functionLexemeIndex == lexemeList.size() - 1)
            return null;

        Lexeme paramLexeme = lexemeList.get(functionLexemeIndex + 1);

        if (paramLexeme.type != Lexeme.LexemeType.NUMBERLITERAL)
            return null;

        return paramLexeme.value;
    }

    private static boolean isSameFunctionSyntax(NumValue leftArg, Lexeme functionLexeme, NumValue rightArg, SyntaxDesc expectedSyntax) {
        if (functionLexeme.type != Lexeme.LexemeType.FUNCTION)
            return false;

        return new SyntaxDesc(leftArg, functionLexeme.textValue, rightArg).equals(expectedSyntax);
    }

    private static void proactiveSyntaxErrorCheck(List<Lexeme> unevaluatedLexemeList) throws SyntaxException {
        if (unevaluatedLexemeList.size() == 0)
            throw new EmptyExpressionException();

        if (doesListHaveSuccessivePairOfLexemesWithType(unevaluatedLexemeList, Lexeme.LexemeType.NUMBERLITERAL))
            throw new AdjacentNumericalsException();

        if (doesListHaveSuccessivePairOfLexemesWithType(unevaluatedLexemeList, Lexeme.LexemeType.FUNCTION))
            throw new AdjacentFunctionsException();
    }

    private static void onFailedToEvaluate(List<Lexeme> unevaluatedLexemeList) throws SyntaxException {
        int functionLexemeIndex = getIndexOfAnyFunction(unevaluatedLexemeList);
        if (functionLexemeIndex >= 0) {
            NumValue leftArg = getLeftArg(functionLexemeIndex, unevaluatedLexemeList);
            NumValue rightArg = getRightArg(functionLexemeIndex, unevaluatedLexemeList);

            SyntaxDesc syntaxDesc = new SyntaxDesc(
                    leftArg,
                    unevaluatedLexemeList.get(functionLexemeIndex).textValue,
                    rightArg
            );

            throw new UnrecognisedFunctionException(syntaxDesc);
        }
        else {
            throw new RuntimeException("ParenthesislessSolver: Failed to evaluate lexeme list:\n%s".formatted(unevaluatedLexemeList));
        }
    }

    private static boolean doesListHaveSuccessivePairOfLexemesWithType(List<Lexeme> lexemeList, Lexeme.LexemeType type) {
        Lexeme previousLexeme = null;
        for (Lexeme currentLexeme : lexemeList) {
            if (previousLexeme != null
                    && previousLexeme.type == type
                    && currentLexeme.type == type)
                return true;

            previousLexeme = currentLexeme;
        }

        return false;
    }

    private static int getIndexOfAnyFunction(List<Lexeme> lexemeList) {
        int i = 0;
        for (Lexeme lexeme : lexemeList) {
            if (lexeme.type == Lexeme.LexemeType.FUNCTION)
                return i;

            i++;
        }
        return -1;
    }
}
