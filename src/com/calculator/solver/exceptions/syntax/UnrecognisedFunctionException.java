package com.calculator.solver.exceptions.syntax;

import com.calculator.solver.Lexeme;
import com.calculator.solver.exceptions.SyntaxException;
import com.calculator.solver.mathfunctions.MathFunction;
import com.calculator.solver.mathfunctions.MathFunctions;
import com.calculator.solver.mathfunctions.SyntaxDesc;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UnrecognisedFunctionException extends SyntaxException {

    public UnrecognisedFunctionException(SyntaxDesc funcSyntaxDesc, int firstProblematicCharacterIndex, int lastProblematicCharacterIndex) {
        this.funcSyntaxDesc = funcSyntaxDesc;
        this.firstProblematicCharacterIndex = firstProblematicCharacterIndex;
        this.lastProblematicCharacterIndex = lastProblematicCharacterIndex;
    }

    public UnrecognisedFunctionException(SyntaxDesc funcSyntaxDesc, Lexeme functionLexeme) {
        this(funcSyntaxDesc, functionLexeme.firstCharacterIndex, functionLexeme.lastCharacterIndex);
    }

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
