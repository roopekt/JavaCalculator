package com.calculator.solver.mathfunctions;

import com.calculator.solver.NumValue;
import com.calculator.solver.exceptions.WrongArgumentSignatureException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MathFunction {

    private SyntaxDesc syntaxDesc;
    private MathFunctionDouble mathFunctionDouble;

    public MathFunction(SyntaxDesc syntaxDescriptor, MathFunctionDouble mathFunctionDouble) {
        this.syntaxDesc = syntaxDescriptor;
        this.mathFunctionDouble = mathFunctionDouble;
    }

    public NumValue evaluate(NumValue leftArg, NumValue rightArg) throws WrongArgumentSignatureException {
        throwErrorIfIncorrectSignature(leftArg, rightArg);

        double[] doubleArgs = getDoubleArgumentArray(leftArg, rightArg);
        return new NumValue(mathFunctionDouble.evaluate(doubleArgs));
    }

    private double[] getDoubleArgumentArray(NumValue leftArg, NumValue rightArg) {
        List<NumValue> numValues = Arrays.asList(leftArg, rightArg);

        return numValues.stream()
                .filter(Objects::nonNull)
                .mapToDouble(numValue -> numValue.value)
                .toArray();
    }

    private void throwErrorIfIncorrectSignature(NumValue leftArg, NumValue rightArg) throws WrongArgumentSignatureException {
        SyntaxDesc expectedSignature = this.syntaxDesc;
        SyntaxDesc actualSignature = getArgumentSignature(leftArg, rightArg);

        if (!expectedSignature.equals(actualSignature))
            throw new WrongArgumentSignatureException(expectedSignature, actualSignature);
    }

    private SyntaxDesc getArgumentSignature(NumValue leftArg, NumValue rightArg) {
        return new SyntaxDesc(
                leftArg != null,
                this.syntaxDesc.functionSymbol,
                rightArg != null
        );
    }
}
