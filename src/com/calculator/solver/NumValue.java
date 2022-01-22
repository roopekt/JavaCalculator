package com.calculator.solver;

public class NumValue {
    public double value;

    public NumValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("NumValue(%s)", value);
    }
}