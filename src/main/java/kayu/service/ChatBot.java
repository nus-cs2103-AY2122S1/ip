package kayu.service;

/**
 * DukeChatBot class.
 *
 * This class acts as a communicator to the user on the command line.
 */
public class ChatBot {

    private final static String LOGO = "\n" +
            " /$$   /$$  /$$$$$$  /$$     /$$ /$$   /$$\n" +
            "| $$  /$$/ /$$__  $$|  $$   /$$/| $$  | $$\n" +
            "| $$ /$$/ | $$  \\ $$ \\  $$ /$$/ | $$  | $$\n" +
            "| $$$$$/  | $$$$$$$$  \\  $$$$/  | $$  | $$\n" +
            "| $$  $$  | $$__  $$   \\  $$/   | $$  | $$\n" +
            "| $$\\  $$ | $$  | $$    | $$    | $$  | $$\n" +
            "| $$ \\  $$| $$  | $$    | $$    |  $$$$$$/\n" +
            "|__/  \\__/|__/  |__/    |__/     \\______/ \n";

    private final static String LINE_SPLIT =
            "___________________________________________________________________________";

    private final static String GREETING = "Hello!\n" +
            "I'm Kayu, your alternative personal task management to Duke!\n" +
            "What can I do for you?";

    private final static String BYE = "Bye. Hope to see you again soon!";
    
    private final static String ERROR_ON_SAVE = "Error updating task file.";

    private final static String ERROR_EXIT = "Please check your saved file, exiting program...";

    /**
     * Prints logo.
     */
    public void logo() {
        print(LOGO);
    }

    /**
     * Prints greeting message.
     */
    public void greet() {
        info(GREETING);
    }

    /**
     * Prints a closing statement before program terminates.
     */
    public void exit() {
        info(BYE);
    }

    /**
     * Displays the messaged wrapped with LINE_SPLIT.
     *
     * @param message message to display within LINE_SPLIT
     */
    public void info(String message) {
        if (!message.isBlank()) {
            print(LINE_SPLIT + '\n' + message + '\n' + LINE_SPLIT + '\n');
        }
    }

    /**
     * Generates an error on save message.
     */
    public void errorOnSave() {
        error(ERROR_ON_SAVE);
    }

    /**
     * Generates an exit error message.
     */
    public void exitOnError() {
        info(ERROR_EXIT);
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
        info("=( Error: " + errorMessage);
    }
}
