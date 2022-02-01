package com.calculator.solver.mathfunctions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Describes the syntax for a MathFunction: what symbol needs to be used and what arguments are expected.
 * @see MathFunction
 */
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SyntaxDesc {
    public boolean leftArgPresent;
    public String functionSymbol;
    public boolean rightArgPresent;

    public SyntaxDesc(SyntaxDesc otherDesc) {
        leftArgPresent = otherDesc.leftArgPresent;
        functionSymbol = otherDesc.functionSymbol;
        rightArgPresent = otherDesc.rightArgPresent;
    }
}