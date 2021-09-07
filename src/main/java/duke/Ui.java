package duke;

import java.util.Scanner;

/**
 * Ui helper for display functions.
 */
public class Ui {
    private static final String DIVIDER = "__________________________________________\n";

    /**
     * Prints greeting message for Duke.
     */
    public String greet() {
        return dukePrint("Hello! I'm Duke.\n" + "What can I do for you?");
    }


    /**
     * Prints message with dividers and indentation.
     *
     * @param str message to be printed
     */
    public static String dukePrint(String str) {
        assert !str.isEmpty();
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(str);
        builder.append(DIVIDER);
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
            builder.append("\n");
        }
        builder.append(DIVIDER);
        return builder.toString();
    }

    /**
     * Prints Errors message for Loading Error.
     */
    public String showLoadingError() {
        return printError("Error with loading save file. New save will be created.");
    }

    /**
     * Prints Error message for Duke.
     *
     * @param errorMessage Error Message to be printed
     * @return
     */
    public String printError(String errorMessage) {
        return dukePrint("☹ OOPS!!! " + errorMessage);
    }
}
