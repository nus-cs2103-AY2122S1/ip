package duke;

/**
 * Ui is a static class that handles most of the return statements that the user will see.
 *
 * @author meerian
 */
public class Ui {
    /**
     * Returns the welcome message upon startup of the bot.
     */
    public static String welcomeMessage() {
        return "Hello this is Duke! What can I do for you today? \nuwu";
    }

    /**
     * Returns an error message when an unknown command is entered by user.
     */
    public static String unknownCommand() {
        return "OOPS!!! I'm sorry, but I don't know what that means :(\n"
            + "Type 'help' to learn how to use DukeBot!";
    }

    /**
     * Returns the goodbye message once user closes the bot.
     */
    public static String goodbye() {
        return "Bye, hope to see you again soon!";
    }

    /**
     * Returns a string to indicate that a task has been marked as done.
     */
    public static String markDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task + "\nuwu";
    }

    /**
     * Returns a string to indicate that the list has been saved.
     */
    public static String saveList() {
        return "List saved! \nuwu";
    }

    /**
     * Returns a string to indicate that the input has been formatted wrongly.
     */
    public static String wrongFormat() {
        return "OOPS!!! The task is formatted wrongly.";
    }
}
