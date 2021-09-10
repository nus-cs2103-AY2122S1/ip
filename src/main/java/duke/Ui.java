package duke;

/**
 *  A class that encapsulates the interactions with the user.
 */
public class Ui {
    /**
     * Shows the final message before terminating.
     *
     * @return The formatted error message to be printed on screen
     */
    public static String showByeMessage() {
        return "\tLOOK no more!!\n";
    }

    /**
     * Shows the welcome message when first executing Duke.
     *
     * @return The formatted error message to be printed on screen
     */
    public static String showWelcomeMessage() {
        String logo = "L00K!!";
        StringBuilder result = new StringBuilder("\tHello from \n\tWhat can I do for you\n");
        result.insert(12, logo);
        return result.toString();
    }

    /**
     * Shows the error message when encountering a DukeException.
     *
     * @return The formatted error message to be printed on screen
     */
    public static String showErrorMessage(DukeException e) {
        return "\n\n" + e.toString() + "\n\n" + "\n";
    }

    /**
     * Shows the formatted reply from Duke after receiving an input from the user.
     *
     * @param reply The String representation of the reply provided from Duke
     */
    public static String showReply(String reply) {
        return reply;
    }

    /**
     * Shows the formatted reply for the TaskList addTask method.
     *
     * @param task The name of the task that is added to the taskList
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
        return "\t" + correctReply + task + "\n" + tasksFound;
    }

    /**
     * Shows the formatted reply for the TaskList undoList/redoList method.
     *
     * @param tasks The undone/redone list of tasks
     * @param isUndo True if is for undoList method and false for redoList method
     * @return The String to be returned by the undoList/redoList method
     */
    public static String showUndoRedoListReply(String tasks, boolean isUndo) {
        String type = isUndo ? "undone" : "redone";
        return "\tLast command has been " + type + ". The tasks is now this:\n" + tasks;
    }
}
