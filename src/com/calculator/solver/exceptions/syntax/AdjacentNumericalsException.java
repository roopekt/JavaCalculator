package com.calculator.solver.exceptions.syntax;

import com.calculator.solver.exceptions.SyntaxException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class AdjacentNumericalsException extends SyntaxException {

    @Override
    public String getMessageForUser() { return "A function/operator is missing."; }
}
