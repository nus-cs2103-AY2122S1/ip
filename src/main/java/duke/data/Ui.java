package duke.data;

/**
 * Class that deals with receiving input from the user and sending output to the user.
 */
public class Ui {
    /**
     * Statement to show the user upon exit
     */
    private static final String MESSAGE_WELCOME = "Hello! I'm Cynthius\n"
            + "What can I do for you?\n"
            + "Type 'help' for the list of commands";

    /**
     * Statement to show the user upon running Duke
     */
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    /**
     * Statement to show the user upon finding no saved file
     */
    private static final String MESSAGE_LOADING_ERROR = "No saved tasks were found.";

    /**
     * Statement to show the user when a help keyword is called
     */
    private static final String MESSAGE_HELP = "Commands: \n"
            + "1) help: Displays a guide of all commands. \n"
            + "Example: `help` \n \n"
            + "2) todo: Adds a todo task to the task list. \n"
            + "Format: `todo TASK` \n"
            + "Example: `todo homework` \n \n"
            + "3) event: Adds an event task to the task list. \n"
            + "Format: `event TASK /at TASK_LOCATION` \n"
            + "Example: `event birthday party /at Calvin's house` \n \n"
            + "4) deadline: Adds a deadline task to the task list. \n"
            + "Format: `deadline TASK /by TASK_DATE_TIME` \n"
            + "Example: `deadline ES2660 draft /by 16/9`` \n \n"
            + "5) contact: Adds a contact to the contact list. \n"
            + "Format: `contact NAME (/about NAME_DESCRIPTION)` \n"
            + "Do note that `(/about NAME_DESCRIPTION)` is optional \n"
            + "Example: `contact Calvin /about orbital partner` \n \n"
            + "6) delete: Removes a task/contact from the list. \n"
            + "Format: `delete CONTACT/TASK INDEX` \n"
            + "For `CONTACT/TASK`, `c` represents deleting from the contact list "
            + "while `t` represents deleting from the task list \n"
            + "Example 1: `delete c 1` (delete first contact)\n"
            + "Example 2: `delete t 3` (delete third task) \n \n"
            + "7) done: Marks a task in task list as done. \n"
            + "Format: `done INDEX` \n"
            + "Example: `done 2` (marks second task in task list as done) \n \n"
            + "8) find: Searches for task(s) and contact(s) that contain the keyword(s) \n"
            + "Format: `find KEYWORD(S)` \n"
            + "Example: `find k3soju` \n \n"
            + "9) list: Lists all tasks/contacts currently saved. \n"
            + "Format: `list` \n"
            + "Example: `list` \n \n"
            + "10) bye: Exits Cynthius \n"
            + "Format: `bye` \n"
            + "Example: `bye` \n";


    /**
     * Shows message(s) to the user
     */
    public static String showToUser(String... message) {
        String displayedMessage = "";
        for (String m : message) {
            displayedMessage += (m + "\n");
        }
        return displayedMessage;
    }

    /**
     * Displays welcome message to the user.
     */
    public static String showWelcome() {
        return showToUser(MESSAGE_WELCOME);
    }

    /**
     * Displays goodbye message to the user.
     */
    public static String showGoodbye() {
        return showToUser(MESSAGE_EXIT);
    }

    /**
     * Displays error message to the user when user file is not loaded.
     */
    public static String showLoadingError() {
        return showToUser(MESSAGE_LOADING_ERROR);
    }

    /**
     * Displays confirmation message to the user when task is added.
     */
    public static String showAddedInformation() {
        return showToUser("Information added successfully!");
    }

    /**
     * Displays confirmation message to the user when task is deleted.
     */
    public static String showDeletedInformation() {
        return showToUser("Information deleted successfully!");
    }

    /**
     * Displays confirmation message to the user when task is marked as done.
     */
    public static String showDoneTask() {
        return showToUser("Task marked as done!");
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

    /**
     * Displays a summary of all the keywords in Cynthius to the user.
     *
     * @return HELP_MESSAGE.
     */
    public static String showHelp() {
        return showToUser(MESSAGE_HELP);
    }
}
