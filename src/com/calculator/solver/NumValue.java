package com.calculator.solver;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class NumValue {
    public double value;

    public NumValue(double value) {
        this.value = value;
    }
}