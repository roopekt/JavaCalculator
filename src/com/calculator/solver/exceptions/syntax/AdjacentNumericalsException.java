package com.calculator.solver.exceptions.syntax;

import com.calculator.solver.exceptions.SyntaxException;
import lombok.ToString;

@ToString
public class AdjacentNumericalsException extends SyntaxException {

    @Override
    public String getMessageForUser() { return "A function/operator is missing."; }
}
