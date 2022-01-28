package com.calculator.solver.mathfunctions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SyntaxDesc {
    public boolean leftArgPresent;
    public String functionSymbol;
    public boolean rightArgPresent;
}