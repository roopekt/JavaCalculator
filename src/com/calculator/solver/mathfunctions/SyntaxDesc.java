package com.calculator.solver.mathfunctions;

import com.calculator.solver.NumValue;
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

    public SyntaxDesc(NumValue leftArg, String functionSymbol, NumValue rightArg) {
        leftArgPresent = leftArg != null;
        this.functionSymbol = functionSymbol;
        rightArgPresent = rightArg != null;
    }
}