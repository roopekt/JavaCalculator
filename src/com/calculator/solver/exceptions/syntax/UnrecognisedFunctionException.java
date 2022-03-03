package com.calculator.solver.exceptions.syntax;

import com.calculator.solver.exceptions.SyntaxException;
import com.calculator.solver.mathfunctions.MathFunctions;
import com.calculator.solver.mathfunctions.SyntaxDesc;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class UnrecognisedFunctionException extends SyntaxException {

    public final SyntaxDesc funcSyntaxDesc;

    @Override
    public String getMessageForUser() {
        SyntaxDesc bestMatch = MathFunctions.getAnyMathFunctionWithSymbol(funcSyntaxDesc.functionSymbol).syntaxDesc;

        if (bestMatch == null)
            return "Unrecognised function/operator.";

        String message = "";
        if (bestMatch.leftArgPresent && !funcSyntaxDesc.leftArgPresent) message += "Left argument is missing. ";
        if (bestMatch.rightArgPresent && !funcSyntaxDesc.rightArgPresent) message += "Right argument is missing. ";
        if (!bestMatch.leftArgPresent && funcSyntaxDesc.leftArgPresent) message += "Unexpected left argument. ";
        if (!bestMatch.rightArgPresent && funcSyntaxDesc.rightArgPresent) message += "Unexpected right argument. ";

        return message;
    }
}
