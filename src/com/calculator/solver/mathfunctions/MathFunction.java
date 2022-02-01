package com.calculator.solver.mathfunctions;

import com.calculator.solver.NumValue;
import com.calculator.solver.exceptions.MathException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * All math operations like addition and multiplication are an instance of this class.
 * Holds data about syntax, and functionality to evaluate with any type of supported numbers.
 */
public class MathFunction {

    public final SyntaxDesc syntaxDesc;
    private final MathFunctionDouble mathFunctionDouble;

    public MathFunction(SyntaxDesc syntaxDescriptor, MathFunctionDouble mathFunctionDouble) {
        this.syntaxDesc = syntaxDescriptor;
        this.mathFunctionDouble = mathFunctionDouble;
    }

    public NumValue evaluate(NumValue leftArg, NumValue rightArg) throws MathException {
        throwErrorIfIncorrectSignature(leftArg, rightArg);

        double[] doubleArgs = getDoubleArgumentArray(leftArg, rightArg);
        return new NumValue(mathFunctionDouble.evaluate(doubleArgs));
    }

    private double[] getDoubleArgumentArray(NumValue leftArg, NumValue rightArg) {
        List<NumValue> numValues = Arrays.asList(leftArg, rightArg);

        return numValues.stream()
                .filter(Objects::nonNull)
                .mapToDouble(NumValue::getDouble)
                .toArray();
    }

    private void throwErrorIfIncorrectSignature(NumValue leftArg, NumValue rightArg) {
        SyntaxDesc expectedSignature = this.syntaxDesc;
        SyntaxDesc actualSignature = getArgumentSignature(leftArg, rightArg);

        if (!expectedSignature.equals(actualSignature))
            throw new RuntimeException("Incorrect function signature");
    }

    private SyntaxDesc getArgumentSignature(NumValue leftArg, NumValue rightArg) {
        return new SyntaxDesc(
                leftArg != null,
                this.syntaxDesc.functionSymbol,
                rightArg != null
        );
    }
}
