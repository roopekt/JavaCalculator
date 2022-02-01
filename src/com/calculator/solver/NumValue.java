package com.calculator.solver;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A wrapper for any kind of numerical value.
 */
@ToString
@EqualsAndHashCode
public class NumValue {
    private double doubleValue;

    public double getDouble() {
        return doubleValue;
    }

    public NumValue(double value) {
        this.doubleValue = value;
    }
}