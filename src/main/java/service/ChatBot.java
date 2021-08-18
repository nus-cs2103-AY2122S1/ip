package service;

/**
 * DukeChatBot class.
 *
 * This class acts as a communicator to the user on the command line.
 */
public class ChatBot {

    private final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String LINE_SPLIT =
            "___________________________________________________________________________";

    /**
     * Prints a greeting when program is first booted up.
     */
    public void greet() {
        print("Hello from\n" + LOGO);
        info("Hello! I'm Duke.\nWhat can I do for you?");
    }

    /**
     * Prints a closing statement before program terminates.
     */
    public void exit() {
        info("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the messaged wrapped with LINE_SPLIT.
     *
     * @param message message to display within LINE_SPLIT
     */
    public void info(String message) {
        System.out.println(LINE_SPLIT);
        System.out.println(message);
        System.out.println(LINE_SPLIT + '\n');
    }

    /**
     * Uses the default system's println method to output the message string.
     *
     * @param string string to print in standard format
     */
    public void print(String string) {
        System.out.println(string);
    }

    /**
     * Generates the error template which is then displayed to the user.
     *
     * @param errorMessage error message to display
     */
    public void error(String errorMessage) {
        info("☹ Error: " + errorMessage);
    }
}
