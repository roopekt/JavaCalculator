package com.calculator.solver.exceptions.syntax;

import com.calculator.solver.exceptions.SyntaxException;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Thrown when a number literal cannot be interpreted correctly.
 */
@ToString
@AllArgsConstructor
public class IncorrectNumberLiteralException extends SyntaxException {
    public final String numberLiteral;

    @Override
    public String getMessageForUser() {
        return "Cannot interpret this number.";
    }
}