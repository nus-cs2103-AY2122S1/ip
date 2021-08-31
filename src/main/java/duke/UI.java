package duke;

/**
 * Encapsulates some of the output messages that will be shown to the user.
 */
public class UI {

    private static String addedText = "Got it. I've added this task:\n";

    /**
     * Handles the greeting or opening message that is shown to the user just before inputs are read.
     */
    public static void greet() {
        String greetText = "Hello I'm Duke\nWhat can I do for you?\n";
        System.out.print(greetText);
    }

    public static String getAddedText() {
        return addedText;
    }

    /**
     * Handles the exiting or halting of Duke when the user has given the appropriate input.
     */
    public static String exit() {
        String exitText = "Bye. Hope to see you again soon!";
        return exitText;
    }
}
