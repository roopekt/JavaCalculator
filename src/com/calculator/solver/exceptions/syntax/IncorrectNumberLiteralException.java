package com.calculator.solver.exceptions.syntax;

import com.calculator.solver.Lexeme;
import com.calculator.solver.exceptions.SyntaxException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Thrown when a number literal cannot be interpreted correctly.
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class IncorrectNumberLiteralException extends SyntaxException {

    public IncorrectNumberLiteralException(String numberLiteral, int firstProblematicCharacterIndex, int lastProblematicCharacterIndex) {
        this.numberLiteral = numberLiteral;
        this.firstProblematicCharacterIndex = firstProblematicCharacterIndex;
        this.lastProblematicCharacterIndex = lastProblematicCharacterIndex;
    }

    public IncorrectNumberLiteralException(Lexeme numberLiteral) {
        this(numberLiteral.textValue, numberLiteral.firstCharacterIndex, numberLiteral.lastCharacterIndex);
    }

    public final String numberLiteral;

    @Override
    public String getMessageForUser() {
        return "Cannot interpret this number.";
    }
}
