package com.calculator.solver.exceptions.syntax;

import com.calculator.solver.Lexeme;
import com.calculator.solver.exceptions.SyntaxException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdjacentFunctionsException extends SyntaxException {

    public AdjacentFunctionsException(int firstProblematicCharacterIndex, int lastProblematicCharacterIndex) {
        this.firstProblematicCharacterIndex = firstProblematicCharacterIndex;
        this.lastProblematicCharacterIndex = lastProblematicCharacterIndex;
    }

    public AdjacentFunctionsException(Lexeme leftFunc, Lexeme rightFunc) {
        this(leftFunc.firstCharacterIndex, rightFunc.lastCharacterIndex);
    }

    @Override
    public String getMessageForUser() { return "Two functions/operators cannot appear successively."; }
}
