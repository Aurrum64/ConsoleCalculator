package ru.ncedu.lebedev.utils.consoleCalculator.functions;

import ru.ncedu.lebedev.utils.consoleCalculator.CalculatorOperations;

import java.util.Stack;

/**
 * Class, which implements CalculatorOperations interface and
 * responds for arithmetic operations and functions support.
 *
 * @author Artem Lebedev
 */

public class SqrtFunction implements CalculatorOperations {

    @Override
    public void calculate(Stack<Double> stack) throws ArithmeticException {

        double operand = stack.pop();
        if (operand < 0){
            throw new ArithmeticException("Not a number value!");
        }
        double result = Math.sqrt(operand);
        stack.push(result);
    }

    @Override
    public boolean checkArithmeticSymbol(String symbol) {
        return symbol.equals("sqrt");
    }

    @Override
    public int numberOfOperandsNeeded() {
        return 1;
    }
}
