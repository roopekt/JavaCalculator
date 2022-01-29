package com.calculator.solver.exceptions;

import com.calculator.solver.Lexeme;

public abstract class UserException extends Exception {

    @Override
    public abstract String toString();

    public abstract String getMessageForUser();

    public Lexeme problematicLexeme;
}
