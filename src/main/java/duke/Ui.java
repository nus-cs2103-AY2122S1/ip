package duke;

import duke.exception.InvalidDukeCommandException;

/**
 * A class that handles anything related to the display of Duke.
 */
public class Ui {
    private static final String LINE_BREAK = "--------------------------\n";

    /**
     * Wraps a string between 2 line breaks.
     *
     * @param s String to be wrapped.
     * @return New string between 2 line breaks.
     */
    private static String wrapBetweenLines(String s) {
        return LINE_BREAK + s + "\n" + LINE_BREAK;
    }

    /**
     * Constructs a new instance of Ui. No arguments are expected.
     */
    public Ui() {

    }

    /**
     * Prints the specified message in a standardized format.
     *
     * @param s The message to be printed.
     */
    public void dukePrint(String s) {
        System.out.println(wrapBetweenLines(s));
    }

    /**
     * Prints the specified message in the Duke error message format.
     *
     * @param s The error message to be printed.
     */
    public void dukeShowError(String s) {
        Exception e = new InvalidDukeCommandException(s);
        dukePrint(e.getMessage());
    }
}
