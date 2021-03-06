package com.calculator.solver.exceptions.syntax;

import com.calculator.solver.exceptions.SyntaxException;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EmptyExpressionException extends SyntaxException {

    public EmptyExpressionException() {
        firstProblematicCharacterIndex = 0;
        lastProblematicCharacterIndex = 0;
    }

    @Override
    public String getMessageForUser() {return "Expression is empty."; }
}
