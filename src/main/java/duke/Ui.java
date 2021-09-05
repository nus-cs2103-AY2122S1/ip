package duke;

/**
 *  A class that encapsulates the interactions with the user.
 */
public class Ui {
    /**
     * Shows the welcome message when first executing Duke.
     */
    public static String showWelcomeMessage() {
        String logo = "DUKE!!";
        StringBuilder result = new StringBuilder("Hello from \nWhat can I do for you\n");
        result.insert(11, logo);
        return result.toString();
    }

    /**
     * Shows the error message when encountering a DukeException.
     */
    public static String showErrorMessage(DukeException e) {
        String border = "******************************";
        return border + border + "\n\n"
                + e.toString() + "\n\n"
                + border + border + "\n";
    }

    /**
     * Shows the BreakLine that is shown at the start and end of each Duke's reply.
     */
    public static String showBreakLine() {
        String breakLine = "------------------------------";
        return breakLine + breakLine + "\n";
    }

    /**
     * Shows the formatted reply from Duke after receiving an input from the user.
     *
     * @param reply The String representation of the reply provided from Duke
     */
    public static String showReply(String reply) {
        StringBuilder result = new StringBuilder(reply);
        result.insert(0, showBreakLine()).append(showBreakLine());
        return result.toString();
    }

    /**
     * Shows the formatted reply for the TaskList addTask method.
     *
     * @param task The name of thetask that is added to the taskList
     * @param size The new size of the taskList after adding the task
     * @return The String to be returned by the addTask method
     */
    public static String showAddTaskReply(String task, int size) {
        return "\tGot it. I've added this task:\n\t\t "
                + task
                + "\n\tNow you have " + size + " tasks in the list.\n";
    }

    /**
     * Shows the formatted reply for the TaskList markTask method.
     *
     * @param task The name of the task that has been marked as done
     * @return The String to be returned by the addTask method
     */
    public static String showMarkTaskReply(String task) {
        return "\tNice! I've marked this task as done:\n\t\t" + task + "\n";
    }

    /**
     * Shows the formatted reply for the TaskList deleteTask method.
     *
     * @param task The name of the task that has been deleted
     * @param size The new size of the taskList after deleting the task
     * @return The String to be returned by the addTask method
     */
    public static String showDeleteTaskReply(String task, int size) {
        return "\tNoted. I've removed this task:\n\t\t" + task
                + "\n\tNow you have " + size + " tasks in the list.\n";
    }

    /**
     * Shows the formatted reply for the TaskList findTask method.
     *
     * @param task The name of the task that is to be found
     * @param tasksFound The String representing all the task that is found line by line
     * @return The String to be returned by the findTask method
     */
    public static String showFindTaskReply(String task, String tasksFound) {
        String foundReply = "Here are the tasks matching the keyword: ";
        String notFoundReply = "No task matching the keyword: ";
        String correctReply = tasksFound.isEmpty() ? notFoundReply : foundReply;
        return correctReply + task + "\n" + tasksFound;
    }
}
