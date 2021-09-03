package duke.util;

import java.util.Scanner;

import duke.exception.DukeException;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Encapsulates the UI that interacts with the user through scanner the inputted text from the user and prints
 * messages based on the result of the different Command methods
 *
 * @author Keith Tan
 */
public class Ui {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static Scanner commandScanner;

    public Ui() {
        this.commandScanner = new Scanner(System.in);
    }

    /**
     * Prints out message according to desired format to user
     *
     * @param message message to bve printed
     */
    public void printMessage(String message) {

        String finalMessage = HORIZONTAL_LINE
                + "\n" + message + "\n"
                + HORIZONTAL_LINE;
        System.out.println(finalMessage);
    }

    /**
     * Prints out error message
     *
     * @param de DukeException that was thrown during running of the application
     */
    public void printErrorMessage(DukeException de) {
        printMessage(de.toString());
    }

}
