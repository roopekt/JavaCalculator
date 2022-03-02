package com.calculator.solver.exceptions.syntax;

import com.calculator.solver.exceptions.SyntaxException;
import lombok.ToString;

@ToString
public class EmptyExpressionException extends SyntaxException {

    @Override
    public String getMessageForUser() {return "Expression is empty."; }
}
