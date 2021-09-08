package duke;

/**
 * Ui is a static class that handles most of the print statements that the user will see.
 * @author meerian
 *
 */
public class Ui {
    /**
     * Prints out the welcome message upon startup of the bot.
     *
     */
    public static String welcomeMessage() {
        return "Hello this is Duke! What can I do for you today?";
    }

    /**
     * Prints out an error message when an unknown command is entered by user.
     *
     */
    public static String unknownCommand() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Prints out the goodbye message once user closes the bot.
     *
     */
    public static String goodbye() {
        return "Bye, hope to see you again soon!";
    }


}
