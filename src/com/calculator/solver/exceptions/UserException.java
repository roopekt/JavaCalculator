package com.calculator.solver.exceptions;

import com.calculator.solver.Lexeme;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Base class for all exceptions caused by the user related to evaluating an expression.
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public abstract class UserException extends Exception {

    public abstract String getMessageForUser();

    public int firstProblematicCharacterIndex;
    public int lastProblematicCharacterIndex;

    public void setProblematicLexeme(Lexeme lexeme) {
        firstProblematicCharacterIndex = lexeme.firstCharacterIndex;
        lastProblematicCharacterIndex = lexeme.lastCharacterIndex;
    }
}
