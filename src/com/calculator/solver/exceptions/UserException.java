package com.calculator.solver.exceptions;

import com.calculator.solver.Lexeme;

/**
 * Base class for all exceptions caused by the user related to evaluating an expression.
 */
public abstract class UserException extends Exception {

    @Override
    public abstract String toString();

    public abstract String getMessageForUser();

    public Lexeme problematicLexeme;
}