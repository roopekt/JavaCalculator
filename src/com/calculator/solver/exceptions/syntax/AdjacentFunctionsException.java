package com.calculator.solver.exceptions.syntax;

import com.calculator.solver.Lexeme;
import com.calculator.solver.exceptions.SyntaxException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class AdjacentFunctionsException extends SyntaxException {

    @Override
    public String getMessageForUser() { return "Two functions/operators cannot appear successively."; }
}
