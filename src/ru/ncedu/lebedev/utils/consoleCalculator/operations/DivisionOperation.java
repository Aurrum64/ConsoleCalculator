package ru.ncedu.lebedev.utils.consoleCalculator.operations;

import ru.ncedu.lebedev.utils.consoleCalculator.CalculatorOperations;

import java.util.Stack;

/**
 * Class, which implements CalculatorOperations interface and
 * responds for arithmetic operations and functions support.
 *
 * @author Artem Lebedev
 */

public class DivisionOperation implements CalculatorOperations {

    @Override
    public void calculate(Stack<Double> stack) throws ArithmeticException {

        double operand2 = stack.pop();
        if (operand2 == 0) {
            throw new ArithmeticException("Division by zero is not permitted!");
        }
        double operand1 = stack.pop();
        double result = operand1 / operand2;
        stack.push(result);
    }

    @Override
    public boolean checkArithmeticSymbol(String symbol) {
        return symbol.equals("/");
    }

    @Override
    public int numberOfOperandsNeeded() {
        return 2;
    }
}
