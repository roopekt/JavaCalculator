package com.calculator.solver.mathfunctions.mathfunctionstest;

import com.calculator.solver.NumValue;
import com.calculator.solver.exceptions.MathException;
import com.calculator.solver.exceptions.math.ZeroDivisionException;
import com.calculator.solver.exceptions.syntax.WrongArgumentSignatureException;
import com.calculator.solver.mathfunctions.MathFunction;
import com.calculator.solver.mathfunctions.MathFunctions;
import com.calculator.solver.mathfunctions.SyntaxDesc;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class MathFunctionsTest {

    //descriptors to find the correct MathFunction
    public static final SyntaxDesc plusDesc = new SyntaxDesc(false, "+", true);//one may write just "+2"
    public static final SyntaxDesc negateDesc = new SyntaxDesc(false, "-", true);
    public static final SyntaxDesc powerDesc = new SyntaxDesc(true, "^", true);
    public static final SyntaxDesc multiplyDesc = new SyntaxDesc(true, "*", true);
    public static final SyntaxDesc divideDesc = new SyntaxDesc(true, "/", true);
    public static final SyntaxDesc addDesc = new SyntaxDesc(true, "+", true);
    public static final SyntaxDesc subtractDesc = new SyntaxDesc(true, "-", true);

    @Test
    public void no_duplicate_math_functions() {
        Set<SyntaxDesc> descriptorSet = new HashSet<>();
        for (MathFunction mathFunction : MathFunctions.getMathFunctionsInOrderOfExpectedEvaluation()) {
            SyntaxDesc newDesc = mathFunction.syntaxDesc;

            String errorMessage = String.format("%s appears twice in MathFunctions.mathFunctionsInOrderOfExpectedEvaluation", newDesc);
            assertFalse(errorMessage, descriptorSet.contains(newDesc));

            descriptorSet.add(newDesc);
        }
    }

    public static void assertFindAndEvaluateFunction(SyntaxDesc syntaxDesc, NumValue expectedResult, NumValue leftArg, NumValue rightArg) throws MathException {
        MathFunction function = MathFunctions.getMathFunction(syntaxDesc);

        if (function == null) {
            String message = String.format("failed to find function: %s", syntaxDesc);
            throw new RuntimeException(message);
        }

        NumValue actual;
        try {
            actual = function.evaluate(leftArg, rightArg);
        }
        catch (WrongArgumentSignatureException e) {
            throw new RuntimeException("incorrect argument signature");
        }

        assertEquals(expectedResult, actual);
    }
}