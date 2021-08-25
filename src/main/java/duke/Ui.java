package duke;

import java.util.Scanner;

/**
 * Ui helper for display functions.
 */
public class Ui {
    private static final String divider = "\t____________________________________________________________\n";

    /**
     * Prints greeting message for Duke.
     */
    public void greet() {
        dukePrint("Hello! I'm Duke.\n" + "What can I do for you?");
    }

    /**
     * Prints message with dividers and indentation.
     *
     * @param str message to be printed
     */
     public static void dukePrint(String str) {
        Scanner scanner = new Scanner(str);
        System.out.print(divider);
        while (scanner.hasNextLine()) {
            System.out.print("\t");
            System.out.println(scanner.nextLine());
        }
        System.out.print(divider);
    }

    /**
     * Prints Errors message for Loading Error.
     */
    public void showLoadingError() {
        printError("Error with loading save file. New save will be created.");
    }

    /**
     * Prints Error message for Duke.
     * @param errorMessage Error Message to be printed
     */
    public void printError(String errorMessage) {
        dukePrint( "☹ OOPS!!! " + errorMessage);
    }
}
