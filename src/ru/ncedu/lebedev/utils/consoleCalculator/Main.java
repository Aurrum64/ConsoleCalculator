package ru.ncedu.lebedev.utils.consoleCalculator;

import java.io.FileNotFoundException;

/**
 * @author Artem Lebedev
 */

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InstantiationException, IllegalAccessException {
        ConsoleCalculator calc = new ConsoleCalculator();

        calc.calcInit();
    }
}
