package duke;

import java.time.format.DateTimeFormatter;

/**
 * This class contains some common utility methods regrading text ui.
 */
public class Ui {
    /** Text UI: horizontal line. */
    public static final String HORIZONTAL_LINE = "  -----------------------";
    /** Text UI: indentation. */
    public static final String INDENTATION = "    ";
    /** Text UI: date time format. */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy");

    /**
     * Prints greeting message.
     */
    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Hello, I am Hagician.");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the given string with horizontal line and indentation.
     *
     * @param s The given string.
     */
    public static void showMessage(String s) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + s);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints multilines message in the console.
     *
     * @param input The given multiline message.
     */
    public static void showMultiLines(String input) {
        String[] strings = input.split("\n");
        System.out.println(Ui.HORIZONTAL_LINE);
        for (String string : strings) {
            if (!string.equals("")) {
                System.out.println(Ui.INDENTATION + string);
            }
        }
        System.out.println(Ui.HORIZONTAL_LINE);
    }
}
