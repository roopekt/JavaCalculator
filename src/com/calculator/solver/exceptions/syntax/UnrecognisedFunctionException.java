package com.calculator.solver.exceptions.syntax;

import com.calculator.solver.exceptions.SyntaxException;
import com.calculator.solver.mathfunctions.MathFunction;
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
        MathFunction bestMatch = MathFunctions.getAnyMathFunctionWithSymbol(funcSyntaxDesc.functionSymbol);

        if (bestMatch == null)
            return "Unrecognised function/operator.";

        String message = "";
        if (bestMatch.syntaxDesc.leftArgPresent && !funcSyntaxDesc.leftArgPresent) message += "Left argument is missing. ";
        if (bestMatch.syntaxDesc.rightArgPresent && !funcSyntaxDesc.rightArgPresent) message += "Right argument is missing. ";
        if (!bestMatch.syntaxDesc.leftArgPresent && funcSyntaxDesc.leftArgPresent) message += "Unexpected left argument. ";
        if (!bestMatch.syntaxDesc.rightArgPresent && funcSyntaxDesc.rightArgPresent) message += "Unexpected right argument. ";

        return message;
    }
}
