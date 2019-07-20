package ru.ncedu.lebedev.utils.consoleCalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Class, which responds for main functionality and
 * logic of console calculator program.
 *
 * @author Artem Lebedev
 */

public class ConsoleCalculator {

    private Scanner input = new Scanner(System.in);
    private Stack<Double> calcStack = new Stack<>();
    private List<CalculatorOperations> operations = new ArrayList<>();

    public void calcInit() throws FileNotFoundException, InstantiationException, IllegalAccessException {
        addCalcOperations();
        message("Welcome to console calculator program! (v1.0)\n");
        message("To see help information, print: \"help\"\n");
        calculator();
    }

    /**
     * Method adds instances of operation's classes in program.
     * Availability of operations and functions customize via configuration file: Configuration.txt
     */

    private void addCalcOperations() throws FileNotFoundException, IllegalAccessException, InstantiationException {

        Class<?> operationClass;

        try {
            Scanner fileInput = new Scanner(new File(ConsoleCalculator.class.getResource("Configuration.txt").getFile()));
            while (fileInput.hasNextLine()) {
                operationClass = Class.forName(fileInput.nextLine());
                CalculatorOperations operation = (CalculatorOperations) operationClass.newInstance();
                operations.add(operation);
            }
        } catch (ClassNotFoundException e) {
            message("Configuration file error: class \"" + e.getMessage() + "\" not found!\n" +
                    "Check your Configuration.txt file.");
            System.exit(-1);
        } catch (NullPointerException e) {
            message("Configuration file have a wrong name or not found, where expected:\n" +
                    "./src/ru/ncedu/lebedev/utils/consoleCalculator/Configuration.txt");
            System.exit(-1);
        }
    }

    /**
     * Main logic of console calculator program.
     */

    private void calculator() {
        double operand;
        double result;

        while (true) {
            try {
                if (calcStack.empty()) {
                    message("Enter first operand: ");
                    operand = checkOperandValue(input.nextLine());
                    calcStack.push(operand);
                } else {
                    message("Use the result, " +
                            "as first operand of next operation? (y/n) ");
                    String answer = input.nextLine();
                    checkSpecialCommands(answer);
                    if (answer.equals("y")) {
                        result = calcStack.pop();
                        calcStack.push(result);
                    } else if (answer.equals("n")) {
                        checkSpecialCommands("clear");
                    } else {
                        throw new IllegalArgumentException("Wrong command!");
                    }
                }
                message("Enter second operand: ");
                operand = checkOperandValue(input.nextLine());
                calcStack.push(operand);
                message("Select operation: ");
                String operationSymbol = input.nextLine();
                checkOperationSupports(operationSymbol);
                calculateResult(operationSymbol);
                result = calcStack.pop();
                message("Result: " + result + "\n");
                calcStack.push(result);
            } catch (NumberFormatException e) {
                message("NumberFormatException: " + e.getMessage() + "\n");
                message("\n");
                message("Restarting calculator...\n");
                calcStack.clear();
                calculator();
            } catch (IllegalArgumentException e) {
                message("IllegalArgumentException: " + e.getMessage() + "\n");
                message("\n");
                message("Restarting calculator...\n");
                calcStack.clear();
                calculator();
            } catch (ArithmeticException e) {
                message("ArithmeticException: " + e.getMessage() + "\n");
                message("\n");
                message("Restarting calculator...\n");
                calcStack.clear();
                calculator();
            }
        }
    }

    /**
     * Method selects necessary arithmetic operation or function
     * and calculates the result.
     *
     * @param operationSymbol - arithmetic operation symbol or name of function
     */

    private void calculateResult(String operationSymbol) {
        for (CalculatorOperations operation : operations) {
            if (operation.checkArithmeticSymbol(operationSymbol)) {
                operation.calculate(calcStack);
                break;
            }
        }
    }

    /**
     * Method checks, if the operationSymbol supports
     * in console calculator program.
     *
     * @param operationSymbol - arithmetic operation symbol or name of function
     */

    private void checkOperationSupports(String operationSymbol) {
        input.skip("");
        checkSpecialCommands(operationSymbol);
        boolean passed = false;
        for (CalculatorOperations operation : operations) {
            if (operation.checkArithmeticSymbol(operationSymbol) && calcStack.size() >= operation.numberOfOperandsNeeded()) {
                passed = true;
                break;
            }
        }
        if (!passed) {
            throw new IllegalArgumentException("Such operation is not supported" +
                    " or more, than one symbol in one line!");
        }
    }

    /**
     * Method checks input information from user on special commands.
     *
     * @param inputValue - some information from user (operand, operationSymbol, command etc.).
     */

    private void checkSpecialCommands(String inputValue) {
        input.skip("");
        if (inputValue.equals("exit")) {
            message("Calculator closed.");
            System.exit(0);
        } else if (inputValue.equals("clear")) {
            calcStack.clear();
            calculator();
        } else if (inputValue.equals("help")) {
            printHelp();
            checkSpecialCommands("clear");
        }
    }

    /**
     * Method checks input operands, if it is really operands (number format).
     *
     * @param inputValue - input operand from user.
     * @return - operand, if it is number, else - throw new NumberFormatException();
     */

    private double checkOperandValue(String inputValue) {
        checkSpecialCommands(inputValue);
        input.skip("");
        double operand;
        try {
            operand = Double.parseDouble(inputValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Operand can contains only integer or floating numbers," +
                    " or more, than one operand in one line!");
        }
        return operand;
    }

    private void message(String message) {
        System.out.print(message);
    }

    private void printHelp() {
        message("---- Console calculator help information ----\n");
        message("------------ Available commands: ------------\n");
        message("------ clear - clear calculator stack -------\n");
        message("---- exit - exit from calculator program ----\n");
    }
}
