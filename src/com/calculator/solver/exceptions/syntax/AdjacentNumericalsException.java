package com.calculator.solver.exceptions.syntax;

import com.calculator.solver.Lexeme;
import com.calculator.solver.exceptions.SyntaxException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdjacentNumericalsException extends SyntaxException {

    public AdjacentNumericalsException(int firstProblematicCharacterIndex, int lastProblematicCharacterIndex) {
        this.firstProblematicCharacterIndex = firstProblematicCharacterIndex;
        this.lastProblematicCharacterIndex = lastProblematicCharacterIndex;
    }

    public AdjacentNumericalsException(Lexeme leftNumerical, Lexeme rightNumerical) {
        this(leftNumerical.firstCharacterIndex, rightNumerical.lastCharacterIndex);
    }

    @Override
    public String getMessageForUser() { return "A function/operator is missing."; }
}
