package com.calculator.solver.exceptions;

import com.calculator.solver.Lexeme;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Base class for all exceptions caused by the user related to evaluating an expression.
 */
@EqualsAndHashCode(callSuper = false)
public abstract class UserException extends Exception {

    @Override
    public abstract String toString();

    public abstract String getMessageForUser();

    @Getter
    @Setter
    private Lexeme problematicLexeme;
}
