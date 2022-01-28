package com.calculator.solver.exceptions;

import com.calculator.solver.mathfunctions.SyntaxDesc;
import lombok.NonNull;
import lombok.ToString;

@ToString
public final class WrongArgumentSignatureException extends SyntaxException {

    public final SyntaxDesc expectedSignature;
    public final SyntaxDesc actualSignature;

    public WrongArgumentSignatureException(@NonNull SyntaxDesc expectedSignature, @NonNull SyntaxDesc actualSignature) {
        this.expectedSignature = expectedSignature;
        this.actualSignature = actualSignature;

        if (expectedSignature.equals(actualSignature))
            throw new IllegalArgumentException("WrongArgumentSignatureException.constructor: expectedSignature must not equal actualSignature");

        if (!expectedSignature.functionSymbol.equals(actualSignature.functionSymbol))
            throw new IllegalArgumentException("WrongArgumentSignatureException.constructor: expectedSignature and actualSignature must have the same function symbol");
    }

    public enum SpecificErrorType {
        MISSING_LEFT_ARG,
        UNEXPECTED_LEFT_ARG,
        MISSING_RIGHT_ARG,
        UNEXPECTED_RIGHT_ARG,
        WRONG_FUNCTION_SYMBOL
    }

    @Override
    public String getMessageForUser() {
        return switch (getSpecificErrorType()) {
            case MISSING_LEFT_ARG -> "Left argument is missing.";
            case UNEXPECTED_LEFT_ARG -> "Cannot have a left argument.";
            case MISSING_RIGHT_ARG -> "Right argument is missing.";
            case UNEXPECTED_RIGHT_ARG -> "Cannot have a right argument.";
            case WRONG_FUNCTION_SYMBOL ->
                    throw new RuntimeException("WrongArgumentSignatureException.getMessageForUser: not expecting WRONG_FUNCTION_SYMBOL");
        };
    }

    public SpecificErrorType getSpecificErrorType() {
        if (expectedSignature.leftArgPresent && !actualSignature.leftArgPresent)
            return SpecificErrorType.MISSING_LEFT_ARG;
        else if (!expectedSignature.leftArgPresent && actualSignature.leftArgPresent)
            return SpecificErrorType.UNEXPECTED_LEFT_ARG;
        else if (expectedSignature.rightArgPresent && !actualSignature.rightArgPresent)
            return SpecificErrorType.MISSING_RIGHT_ARG;
        else if (!expectedSignature.rightArgPresent && actualSignature.rightArgPresent)
            return SpecificErrorType.UNEXPECTED_RIGHT_ARG;
        else if (expectedSignature.functionSymbol != actualSignature.functionSymbol)
            return SpecificErrorType.WRONG_FUNCTION_SYMBOL;
        else
            throw new RuntimeException("WrongArgumentSignatureException.getSpecificErrorType: expectedSignature must not match actualSignature");
    }
}
