package com.calculator;

import com.calculator.solver.Solver;
import com.calculator.solver.exceptions.MathException;
import com.calculator.solver.exceptions.SyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The front end of the application.
 */
public class Terminal {

    public static int mainLoop() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                System.out.print(">");
                String expressionToEvaluate = input.readLine();

                evaluateExpressionAndPrintResult(expressionToEvaluate);

                System.out.println();
            }
        }
        catch (Throwable throwable) {
            printUnexpectedThrowable(throwable);

            System.out.println();
            waitForEnter();

            return -1;
        }
    }

    private static void evaluateExpressionAndPrintResult(String expression) {
        try {
            double result = Solver.evaluateExpression(expression).getDouble();
            System.out.println("= " + doubleToString(result));
        }
        catch (SyntaxException exception) {
            System.out.println("Typo. " + exception.getMessageForUser());
        }
        catch (MathException exception) {
            System.out.println("Can't compute. " + exception.getMessageForUser());
        }
        catch (Throwable throwable) {
            printUnexpectedThrowable(throwable);
        }
    }

    private static String doubleToString(double d) {
        return Double.toString(d);
    }

    private static void printUnexpectedThrowable(Throwable throwable) {
        System.out.println("Congrats! You found a bug!");
        throwable.printStackTrace(System.out);
    }

    private static void waitForEnter() {
        System.out.print("Press enter to continue.");

        try {
            System.in.read();
        }
        catch (IOException exception) {
            printUnexpectedThrowable(exception);
        }
    }
}
