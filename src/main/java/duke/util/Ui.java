package duke.util;

/**
 * Represents a user interface which deals with interactions with the user.
 */
public class Ui {

    /**
     * Prints a welcome message to the user.
     */
    public void printUserGreeting() {
        final String LOGO = " ____        _        \n"
                          + "|  _ \\ _   _| | _____ \n"
                          + "| | | | | | | |/ / _ \\\n"
                          + "| |_| | |_| |   <  __/\n"
                          + "|____/ \\__,_|_|\\_\\___|\n";
        final String GREETING = "Greetings friend! I am your personal assistant,\n" + LOGO
                                + "\nWhat can I do for you?\n";
        System.out.println(GREETING);
    }

    /**
     * Prints a message with a line of dashes before and after it.
     * 
     * @param message The message to print within 2 lines.
     */
    public void printFormattedMessage(String message) {
        String LINE = "\t----------------------------------------------------\n";
        System.out.println(LINE + "\t" + message + LINE);
    }
}
