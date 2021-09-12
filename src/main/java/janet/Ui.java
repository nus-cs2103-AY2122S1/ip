package janet;

/**
 * Encapsulates methods needed for Janet to interact with the user through printing
 * messages to the console.
 */
public class Ui {

    public static final String EXPECTED_SCHEDULE_QUERY = "The date for which you wish to view tasks cannot be empty.";
    public static final String EXPECTED_DATE_GOT_OTHER =
            "The date for which you wish to view tasks should be of the format YYYY-MM-DD.";
    static final String EXPECTED_SEARCH_QUERY = "The search query of a find operation cannot be empty.";
    static final String INTRO_STRING = "Hey there! I'm Good Janet. How many I help you today?";
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
    static final String INDEX_OUT_OF_BOUNDS_STRING = "Sorry, there is no task with the index number ";

    /**
     * Returns a string to confirm that a task has been added to the list.
     *
     * @param task Task that has been added
     * @param size Total number of tasks in the list
     * @return String confirming that the task has been added
     */
    public static String formatTaskAddedString(Task task, int size) {
        assert(task != null);
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
    public static String formatTaskDoneString(Task task) {
        assert(task != null);
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
    public static String formatTaskDeletedString(Task task, int size) {
        assert(task != null);
        return String.format("Certainly, I've deleted this task: \n"
                        + "\t%s"
                        + "\nNow, you have %d tasks in the list.\n",
                task, size);
    }

    /**
     * Returns a string that is shown when a user tries to access a non-existent task index.
     * @param index The index of the non-existent task that the user tried to access
     * @return String to inform the user of index out of bounds error
     */
    public static String formatOutOfBoundsString(int index) {
        return INDEX_OUT_OF_BOUNDS_STRING + index + ".";
    }
}
