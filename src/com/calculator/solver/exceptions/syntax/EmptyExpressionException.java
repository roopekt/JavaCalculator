package com.calculator.solver.exceptions.syntax;

import com.calculator.solver.exceptions.SyntaxException;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class EmptyExpressionException extends SyntaxException {

    @Override
    public String getMessageForUser() {return "Expression is empty."; }
}
