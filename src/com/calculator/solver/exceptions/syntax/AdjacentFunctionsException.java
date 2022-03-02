package com.calculator.solver.exceptions.syntax;

import com.calculator.solver.exceptions.SyntaxException;
import lombok.ToString;

@ToString
public class AdjacentFunctionsException extends SyntaxException {

    @Override
    public String getMessageForUser() { return "Two functions/operators cannot appear successively."; }
}
