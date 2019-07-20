package ru.ncedu.lebedev.utils.consoleCalculator;

import java.util.Stack;

/**
 * Interface, which all calculator's operations and functions must implements.
 *
 * @author Artem Lebedev
 */

public interface CalculatorOperations {

    /**
     * Method calculates result of arithmetic operation or function
     * and push it in calculator stack.
     *
     * @param stack - calculator's stack with user's operands.
     * @throws ArithmeticException if mathematics rules are not followed.
     */

    void calculate(Stack<Double> stack) throws ArithmeticException;

    /**
     * Method check input symbol on belonging to this arithmetic operation.
     *
     * @param symbol - input symbol.
     * @return - true, if check passed, else - return false.
     */

    boolean checkArithmeticSymbol(String symbol);

    /**
     * @return - number of operands, which needs for calculate operation result.
     */

    int numberOfOperandsNeeded();
}
