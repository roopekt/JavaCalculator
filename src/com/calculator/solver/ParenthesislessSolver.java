package com.calculator.solver;

import com.calculator.solver.exceptions.MathException;
import com.calculator.solver.exceptions.SyntaxException;
import com.calculator.solver.exceptions.UserException;
import com.calculator.solver.exceptions.syntax.*;
import com.calculator.solver.mathfunctions.MathFunction;
import com.calculator.solver.mathfunctions.MathFunctions;
import com.calculator.solver.mathfunctions.SyntaxDesc;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

/**
 * Functionality to evaluate an expression that has no parenthesis.
 * @see #evaluateParethesislessExpression(List lexemeList)
 */
public class ParenthesislessSolver {

    public static NumValue evaluateParethesislessExpression(List<Lexeme> lexemeList) throws MathException, SyntaxException {
        proactiveSyntaxErrorCheck(lexemeList);

        evaluateNumberLiteralsInLexemeList(lexemeList);

        for (MathFunction mathFunction : MathFunctions.getMathFunctionsInOrderOfExpectedEvaluation()) {
            evaluateAllInstancesOfFunctionInPlace(mathFunction, lexemeList);
        }

        if (lexemeList.size() != 1 || lexemeList.get(0).type != Lexeme.LexemeType.NUMBERLITERAL) {
            onFailedToEvaluate(lexemeList);
            return null;
        }

        return lexemeList.get(0).value;
    }

    private static void evaluateNumberLiteralsInLexemeList(List<Lexeme> lexemeList) throws IncorrectNumberLiteralException {
        for (Lexeme lexeme : lexemeList) {
            if (lexeme.type == Lexeme.LexemeType.NUMBERLITERAL) {
                double doubleValue = getDoubleValueOfNumberLiteral(lexeme);
                lexeme.value = new NumValue(doubleValue);
            }
        }
    }

    private static double getDoubleValueOfNumberLiteral(Lexeme numberLiteral) throws IncorrectNumberLiteralException {
        boolean nothingExtra = Pattern.compile("[0-9]*\\.?[0-9]*")//0 or more numbers, maybe a dot, 0 or more numbers
                .matcher(numberLiteral.textValue).matches();
        if (!nothingExtra)
            throw new IncorrectNumberLiteralException(numberLiteral);

        try {
            String noSpacesLiteral = numberLiteral.textValue.replace(" ", "");
            return Double.parseDouble(noSpacesLiteral);
        }
        catch (NumberFormatException exception) {
            throw new IncorrectNumberLiteralException(numberLiteral);
        }
    }

    private static void evaluateAllInstancesOfFunctionInPlace(MathFunction function, List<Lexeme> lexemeList) throws MathException {
        for (int i = 0; i < lexemeList.size(); i++) {
            Lexeme leftArg = getLeftArg(i, lexemeList);
            NumValue leftArgValue = leftArg != null ? leftArg.value : null;

            Lexeme operatorLexeme = lexemeList.get(i);

            Lexeme rightArg = getRightArg(i, lexemeList);
            NumValue rightArgValue = rightArg != null ? rightArg.value : null;

            if (isSameFunctionSyntax(leftArgValue, operatorLexeme, rightArgValue, function.syntaxDesc)) {
                NumValue evaluationResult = evaluateFunction(function, leftArgValue, operatorLexeme, rightArgValue);

                Lexeme resultLexeme = getResultLexeme(evaluationResult, leftArg, operatorLexeme, rightArg);

                PlaceEvaluationResultIntoLexemeList(evaluationResult, function.syntaxDesc, i, lexemeList);

                i = -1;//not 0, because i will be incremented right after
            }
        }
    }

    private static NumValue evaluateFunction(MathFunction function, NumValue leftArg, Lexeme operatorLexeme, NumValue rightArg) throws MathException {
        try {
            return function.evaluate(leftArg, rightArg);
        }
        catch (MathException exception) {
            exception.setProblematicLexeme(operatorLexeme);
            throw exception;
        }
    }

    private static Lexeme getResultLexeme(NumValue resultValue, Lexeme leftArg, Lexeme operatorLexeme,  Lexeme rightArg) {
        Lexeme resultLexeme = new Lexeme(resultValue);

        Lexeme leftmostLexeme = leftArg != null ? leftArg : operatorLexeme;
        Lexeme rightmostLexeme = rightArg != null ? rightArg : operatorLexeme;

        resultLexeme.firstCharacterIndex = leftmostLexeme.firstCharacterIndex;
        resultLexeme.lastCharacterIndex = rightmostLexeme.lastCharacterIndex;

        return resultLexeme;
    }

    private static void PlaceEvaluationResultIntoLexemeList(NumValue result, SyntaxDesc syntaxDesc, int functionLexemeIndex, List<Lexeme> lexemeList) {
        lexemeList.set(functionLexemeIndex, new Lexeme(result));

        if (syntaxDesc.rightArgPresent)
            lexemeList.remove(functionLexemeIndex + 1);

        if (syntaxDesc.leftArgPresent)
            lexemeList.remove(functionLexemeIndex - 1);
    }

    private static Lexeme getLeftArg(int functionLexemeIndex, List<Lexeme> lexemeList) {
        if (functionLexemeIndex == 0)
            return null;

        Lexeme paramLexeme = lexemeList.get(functionLexemeIndex - 1);

        if (paramLexeme.type != Lexeme.LexemeType.NUMBERLITERAL)
            return null;

        return paramLexeme;
    }

    private static Lexeme getRightArg(int functionLexemeIndex, List<Lexeme> lexemeList) {
        if (functionLexemeIndex == lexemeList.size() - 1)
            return null;

        Lexeme paramLexeme = lexemeList.get(functionLexemeIndex + 1);

        if (paramLexeme.type != Lexeme.LexemeType.NUMBERLITERAL)
            return null;

        return paramLexeme;
    }

    private static boolean isSameFunctionSyntax(NumValue leftArg, Lexeme functionLexeme, NumValue rightArg, SyntaxDesc expectedSyntax) {
        if (functionLexeme.type != Lexeme.LexemeType.FUNCTION)
            return false;

        return new SyntaxDesc(leftArg, functionLexeme.textValue, rightArg).equals(expectedSyntax);
    }

    private static void proactiveSyntaxErrorCheck(List<Lexeme> unevaluatedLexemeList) throws SyntaxException {
        if (unevaluatedLexemeList.size() == 0)
            throw new EmptyExpressionException();

        LexemePair numericalPair = getSuccessivePairOfLexemesOfType(unevaluatedLexemeList, Lexeme.LexemeType.NUMBERLITERAL);
        if (numericalPair != null)
            throw new AdjacentNumericalsException(numericalPair.first, numericalPair.last);

        LexemePair functionPair = getSuccessivePairOfLexemesOfType(unevaluatedLexemeList, Lexeme.LexemeType.FUNCTION);
        if (functionPair != null)
            throw new AdjacentFunctionsException(functionPair.first, functionPair.last);
    }

    private static void onFailedToEvaluate(List<Lexeme> partiallyEvaluatedLexemeList) throws SyntaxException {
        List<Lexeme> lexemes = partiallyEvaluatedLexemeList;

        int functionLexemeIndex = getIndexOfAnyFunction(lexemes);
        if (functionLexemeIndex >= 0) {
            Lexeme leftArg = getLeftArg(functionLexemeIndex, lexemes);
            Lexeme rightArg = getRightArg(functionLexemeIndex, lexemes);

            SyntaxDesc syntaxDesc = new SyntaxDesc(
                    leftArg != null,
                    lexemes.get(functionLexemeIndex).textValue,
                    rightArg != null
            );

            throw new UnrecognisedFunctionException(syntaxDesc, lexemes.get(functionLexemeIndex));
        }
        else {
            throw new RuntimeException("ParenthesislessSolver: Failed to evaluate lexeme list.\nFinal list: %s".formatted(lexemes));
        }
    }

    private static LexemePair getSuccessivePairOfLexemesOfType(List<Lexeme> lexemeList, Lexeme.LexemeType type) {
        Lexeme previousLexeme = null;
        for (Lexeme currentLexeme : lexemeList) {
            if (previousLexeme != null
                    && previousLexeme.type == type
                    && currentLexeme.type == type)
                return new LexemePair(previousLexeme, currentLexeme);

            previousLexeme = currentLexeme;
        }

        return null;
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    private static class LexemePair {
        Lexeme first;
        Lexeme last;
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
