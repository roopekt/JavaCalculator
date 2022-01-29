package com.calculator.solver.mathfunctions;

import com.calculator.solver.exceptions.math.ZeroDivisionException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class MathFunctions {

    public static List<MathFunction> getMathFunctionsInOrderOfExpectedEvaluation() {
        return mathFunctionsInOrderOfExpectedEvaluation;
    }

    private static final List<MathFunction> mathFunctionsInOrderOfExpectedEvaluation = Collections.unmodifiableList(Arrays.asList(
            new MathFunction(
                    new SyntaxDesc(false, "+", true),
                    args -> args[0]
            ),
            new MathFunction(
                    new SyntaxDesc(false, "-", true),
                    args -> -args[0]
            ),
            new MathFunction(
                    new SyntaxDesc(true, "*", true),
                    args -> args[0] * args[1]
            ),
            new MathFunction(
                    new SyntaxDesc(true, "/", true),
                    args -> divideDoublesWithExceptions(args[0], args[1])
            ),
            new MathFunction(
                    new SyntaxDesc(true, "+", true),
                    args -> args[0] + args[1]
            ),
            new MathFunction(
                    new SyntaxDesc(true, "-", true),
                    args -> args[0] - args[1]
            )
    ));

    public static MathFunction getMathFunction(SyntaxDesc descriptor) {
        return getMathFunctionsInOrderOfExpectedEvaluation()
                .stream()
                .filter(mathFunc -> mathFunc.syntaxDesc.equals(descriptor))
                .findAny()
                .orElse(null);
    }

    public static int getIndexOfMathFunction(SyntaxDesc descriptor) {
        List<MathFunction> mathFunctions = getMathFunctionsInOrderOfExpectedEvaluation();
        int index = IntStream.range(0, mathFunctions.size())
                .filter(i -> mathFunctions.get(i).syntaxDesc.equals(descriptor))
                .findAny()
                .orElse(-1);

        if (index == -1)
            throw new RuntimeException(String.format("%s was not found", descriptor));

        return index;
    }

    private static double divideDoublesWithExceptions(double numerator, double denominator) throws ZeroDivisionException {

        double tolerance = 1e-300;
        if (-tolerance <= denominator && denominator <= tolerance) {
            throw new ZeroDivisionException(numerator, denominator);
        }

        return numerator / denominator;
    }
}
