package duke.util;

import static java.lang.Math.max;

/**
 * Class to handle the UI shown to the end user.
 */
public class Message {
    private static final int BOX_LENGTH = 80;
    private static final int INDENT_LENGTH = 4;
    private static final String INDENT = " ".repeat(INDENT_LENGTH);

    // Message methods

    /**
     * Prints a horizontal line to the console
     */
    private static void horizontal_line() {
        System.out.print("_".repeat(BOX_LENGTH) + "\n");
    }

    /**
     * Displays the message in a text box
     * @param message The message to display
     */
    public static void display_message(String message) {
        horizontal_line();
        String[] lineArr = message.split("\n");
        // Print sides of the box
        for (String line : lineArr) {
            int remainingSpace = max(BOX_LENGTH - line.length() - INDENT_LENGTH - 2, 0);
            System.out.println("|" + INDENT + line + " ".repeat(remainingSpace) + "|");
        }
        horizontal_line();
    }

}
