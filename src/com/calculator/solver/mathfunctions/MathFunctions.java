package com.calculator.solver.mathfunctions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MathFunctions {

    public static List<MathFunction> getMathFunctionsInOrderOfExpectedEvaluation() {
        return mathFunctionsInOrderOfExpectedEvaluation;
    }

    private static final List<MathFunction> mathFunctionsInOrderOfExpectedEvaluation = Collections.unmodifiableList(Arrays.asList(
            new MathFunction(
                    new SyntaxDesc(true, "*", true),
                    p -> p[0] * p[1]
            ),
            new MathFunction(
                    new SyntaxDesc(true, "+", true),
                    p -> p[0] + p[1]
            )
    ));
}
