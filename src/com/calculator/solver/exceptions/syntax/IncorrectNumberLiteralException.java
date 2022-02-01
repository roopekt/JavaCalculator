package com.calculator.solver.exceptions.syntax;

import com.calculator.solver.exceptions.SyntaxException;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class IncorrectNumberLiteralException extends SyntaxException {
    public final String numberLiteral;

    @Override
    public String getMessageForUser() {
        return "Cannot interpret this number.";
    }
}
