package duke.util;

/**
 * Class to deal with interactions with the user via messages.
 * OUTDATED. Unused in Level 10 as GUI has been implemented.
 *
 * @author Benedict Chua
 */
public class Ui {
    private static final String INDENTATION = "     ";
    private static final String LINE_SEPARATOR = "    ____________________________________________________________";
    private static final String[] GREETING = {
        "Hey! I'm Duke (Tsun)!", "What do you want?",
        "...It's not like I want to help you or anything!"
    };
    private static final String[] GOODBYE = {"Hmph! It's not like I want to see you again or anything!"};

    /**
     * Prints messages line by line with indentation.
     *
     * @param messages an array of String that contains the messages to be printed
     */
    public static void displayMessage(String[] messages) {
        for (String message : messages) {
            System.out.println(INDENTATION + message);
        }
    }

    /**
     * Prints line separator.
     */
    public static void printLineSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints empty line.
     */
    public static void printEmptyLine() {
        System.out.println();
    }

    /**
     * Prints greeting salutations.
     * Used on start-up of program.
     */
    public static void showWelcome() {
        displayMessage(GREETING);
    }

    /**
     * Prints farewell salutation.
     * Used when program stops.
     */
    public static void bidFarewell() {
        displayMessage(GOODBYE);
    }
}
