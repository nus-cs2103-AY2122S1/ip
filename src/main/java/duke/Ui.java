package duke;

/**
 * Encapsulates methods needed for Duke to interact with the user through printing
 * messages to the console.
 */
public class Ui {

    static final String EXPECTED_SEARCH_QUERY = "The search query of a find operation cannot be empty.";
    static final String INTRO_STRING = "Hey there! I'm Good Duke. How many I help you today?";
    static final String OUTRO_STRING = "That was an excellent chat - I look forward to seeing you again soon!";
    static final String READ_SAVE_STRING = "Sorry, there was a problem reading the save file :(";
    static final String WRITE_SAVE_STRING = "Sorry, there was a problem saving your tasks :(";
    static final String NEW_SAVE_STRING = "Empty save file detected - loading a blank list.";
    static final String EXPECTED_DONE_INDEX_GOT_NONE =
            "You need to indicate which task number should be marked as done.";
    static final String EXPECTED_DONE_INDEX_GOT_OTHER =
            "The task to be marked as done should be indicated its list index.";
    static final String EXPECTED_TO_DO_DESCRIPTION = "The description of a todo cannot be empty.";
    static final String EXPECTED_DEADLINE_DESCRIPTION = "The description of a deadline cannot be empty.";
    static final String EXPECTED_DEADLINE_BY = "The done-by date of a deadline cannot be empty.";
    static final String EXPECTED_EVENT_DESCRIPTION = "The description of an event cannot be empty.";
    static final String EXPECTED_EVENT_AT = "The timing of an event cannot be empty.";
    static final String EXPECTED_DELETED_INDEX_GOT_NONE = "You need to indicate which task number should be deleted.";
    static final String EXPECTED_DELETED_INDEX_GOT_OTHER = "The task to be deleted should be indicated its list index.";
    static final String UNRECOGNISED_OPERATION = "Sorry, I do not understand this command.";
    static final String SHOW_MATCHES_STRING = "Here are the matching tasks in your list:";
    static final String INDEX_OUT_OF_BOUNDS_STRING = "Sorry, there is no task with the index number ";

    /**
     * Returns a string representation of the tasks in the list.
     *
     * @param taskList Duke's list of tasks
     * @return String representation of list
     */
    public static String taskListString(TaskList taskList) {
        String output = "These are the tasks you've told me:\n";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("%d. %s\n", i + 1, taskList.get(i));
        }
        return output;
    }

    /**
     * Returns a string to confirm that a task has been added to the list.
     *
     * @param task Task that has been added
     * @param size Total number of tasks in the list
     * @return String confirming that the task has been added
     */
    public static String addedString(Task task, int size) {
        return String.format("Alright, I've added this task: \n"
                        + "\t%s\n"
                        + "Now, you have %d tasks in the list.\n",
                task, size);
    }

    /**
     * Returns a string to confirm that a task has marked as complete.
     *
     * @param task The task that has been marked as complete
     * @return String confirming that the task has been marked as complete
     */
    public static String doneString(Task task) {
        return String.format("Certainly, I've marked this task as done: \n"
                        + "\t%s\n",
                task);
    }

    /**
     * Returns a string to confirm that a task has been deleted from the list.
     *
     * @param task Task that has been deleted
     * @param size Total number of tasks left in the list
     * @return String confirming that the task has been deleted
     */
    public static String deletedString(Task task, int size) {
        return String.format("Certainly, I've deleted this task: \n"
                        + "\t%s"
                        + "\nNow, you have %d tasks in the list.\n",
                task, size);
    }

    /**
     * Returns a string that lists the tasks that match a particular search query.
     *
     * @param filteredList A list of filtered tasks
     * @return A string showing the list of filtered tasks
     */
    public static String matchesString(TaskList filteredList) {
        return SHOW_MATCHES_STRING + "\n" + taskListString(filteredList);
    }

    /**
     * Returns a string that is shown when a user tries to access a non-existent task index.
     * @param index The index of the non-existent task that the user tried to access
     * @return String to inform the user of index out of bounds error
     */
    public static String outOfBoundsString(int index) {
        return INDEX_OUT_OF_BOUNDS_STRING + index + ".";
    }
}
