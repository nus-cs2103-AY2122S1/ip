package duke.data;

/**
 * Class that deals with receiving input from the user and sending output to the user.
 */
public class Ui {
    /** Statement to show the user upon exit */
    private static final String MESSAGE_WELCOME = "Hello! I'm Cynthius\n"
            + "What can I do for you?\n"
            + "Type 'help' for the list of commands";

    /** Statement to show the user upon running Duke */
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    /** Statement to show the user upon finding no saved file */
    private static final String MESSAGE_LOADING_ERROR = "No saved tasks were found.";

    /** Shows message(s) to the user */
    public static String showToUser(String... message) {
        String displayedMessage = "";
        for (String m : message) {
            displayedMessage += (m + "\n");
        }
        return displayedMessage;
    }

    /** Displays welcome message to the user. */
    public static String showWelcome() {
        return showToUser(MESSAGE_WELCOME);
    }

    /** Displays goodbye message to the user. */
    public static String showGoodbye() {
        return showToUser(MESSAGE_EXIT);
    }

    /** Displays error message to the user when user file is not loaded. */
    public static String showLoadingError() {
        return showToUser(MESSAGE_LOADING_ERROR);
    }

    /** Displays confirmation message to the user when task is added. */
    public static String showAddedInformation() {
        return showToUser("Information added successfully!");
    }

    /** Displays confirmation message to the user when task is deleted. */
    public static String showDeletedInformation() {
        return showToUser("Information deleted successfully!");
    }

    /** Displays confirmation message to the user when task is marked as done. */
    public static String showDoneTask() {
        return showToUser("Task marked as done!");
    }

    /** Displays message to the user. */
    public static String showMessage(String message) {
        return showToUser(message);
    }

    /**
     * Displays the user's saved task(s) to the user.
     *
     * @param tasks String containing the user's saved tasks.
     * @return A string of the user's saved task(s).
     */
    public static String showTasks(String tasks) {
        if (tasks.isEmpty()) {
            return showToUser("No tasks found!\n");
        }
        return showToUser("Here are your tasks:\n" + tasks);
    }

    /**
     * Displays the user's saved contact(s) to the user.
     *
     * @param contacts String containing the user's saved contacts.
     * @return A string of the user's saved task(s).
     */
    public static String showContacts(String contacts) {
        if (contacts.isEmpty()) {
            return showToUser("No contacts found!\n");
        }
        return showToUser("Here are your contacts:\n" + contacts);
    }
}
