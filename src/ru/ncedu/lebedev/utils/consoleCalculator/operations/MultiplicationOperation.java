package ru.ncedu.lebedev.utils.consoleCalculator.operations;

import ru.ncedu.lebedev.utils.consoleCalculator.CalculatorOperations;

import java.util.Stack;

/**
 * Class, which implements CalculatorOperations interface and
 * responds for arithmetic operations and functions support.
 *
 * @author Artem Lebedev
 */

public class MultiplicationOperation implements CalculatorOperations {

    @Override
    public void calculate(Stack<Double> stack) throws ArithmeticException {

        double result = stack.pop() * stack.pop();
        stack.push(result);
    }

    @Override
    public boolean checkArithmeticSymbol(String symbol) {
        return symbol.equals("*");
    }

    @Override
    public int numberOfOperandsNeeded() {
        return 2;
    }
}
