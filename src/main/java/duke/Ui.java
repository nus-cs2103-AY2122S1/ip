package duke;

/**
 * Encapsulates methods needed for Duke to interact with the user through printing
 * messages to the console.
 */
public class Ui {
    static final String EXPECTED_DONE_INDEX_GOT_NONE = "You need to indicate which task number should be marked as done.";
    static final String EXPECTED_DONE_INDEX_GOT_OTHER = "The task to be marked as done should be indicated its list index.";
    static final String EXPECTED_TO_DO_DESCRIPTION = "The description of a todo cannot be empty.";
    static final String EXPECTED_DEADLINE_DESCRIPTION = "The description of a deadline cannot be empty.";
    static final String EXPECTED_DEADLINE_BY = "The done-by date of a deadline cannot be empty.";
    static final String EXPECTED_EVENT_DESCRIPTION = "The description of an event cannot be empty.";
    static final String EXPECTED_EVENT_AT = "The timing of an event cannot be empty.";
    static final String EXPECTED_DELETED_INDEX_GOT_NONE = "You need to indicate which task number should be deleted.";
    static final String EXPECTED_DELETED_INDEX_GOT_OTHER = "The task to be deleted should be indicated its list index.";
    static final String UNRECOGNISED_OPERATION = "Sorry, I do not understand this command.";
    private static final String INTRO_STRING = "Hey there! I'm Good duke.Duke. How many I help you today?";
    private static final String OUTRO_STRING = "That was an excellent chat - I look forward to seeing you again soon!";
    private static final String READ_SAVE_STRING = "Sorry, there was a problem reading the save file :(";
    private static final String WRITE_SAVE_STRING = "Sorry, there was a problem saving your tasks :(";
    private static final String NEW_SAVE_STRING = "Empty save file detected - loading a blank list.";

    /**
     * Returns a string representation of the tasks in the list.
     *
     * @param taskList Duke's list of tasks
     * @return String representation of list
     */
    public static String taskListString(TaskList taskList) {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("%d. %s\n", i + 1, taskList.get(i));
        }
        return output;
    }

    private String addedString(Task task, int size) {
        return String.format("Alright, I've added this task: \n" +
                        "\t%s\n" +
                        "Now, you have %d tasks in the list.\n",
                task, size);
    }

    private String doneString(Task task) {
        return String.format("Certainly, I've marked this task as done: \n" +
                        "\t%s\n",
                task);
    }

    private String deletedString(Task task, int size) {
        return String.format("Certainly, I've deleted this task: \n" +
                        "\t%s" +
                        "\nNow, you have %d tasks in the list.\n",
                task, size);
    }

    /**
     * Prints a message to the console, bounded by dashed horizontal lines
     * before and after the message.
     *
     * @param str Message to be displayed
     */
    public void print(String str) {
        String horizontalLine = "________________________________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println(str);
        System.out.println(horizontalLine);
    }

    /**
     * Prints the intro message.
     */
    public void showIntro() {
        print(INTRO_STRING);
    }

    /**
     * Prints the outro message.
     */
    public void showOutro() {
        print(OUTRO_STRING);
    }

    /**
     * Prints a confirmation of the task that has been added and the number of
     * tasks being tracked so far.
     *
     * @param task Task that has been added to list
     * @param size Number of tasks in list
     */
    public void showAdded(Task task, int size) {
        print(addedString(task, size));
    }

    /**
     * Prints a message to inform the user that a task has been marked as having
     * been completed.
     *
     * @param task Task that has been marked as done
     */
    public void showDone(Task task) {
        print(doneString(task));
    }

    /**
     * Prints a message to inform the user that a task has been deleted from the
     * list, along with the number of tasks remaining.
     *
     * @param task Task that has been deleted from list
     * @param size Number of remaining tasks
     */
    public void showDeleted(Task task, int size) {
        print(deletedString(task, size));
    }

    /**
     * Prints the list of tasks to the console.
     *
     * @param tasks List of all the tasks
     */
    public void showTasks(TaskList tasks) {
        print(taskListString(tasks));
    }

    /**
     * Prints a message to inform the user about an error in reading the save.
     */
    public void showReadSaveError() {
        print(READ_SAVE_STRING);
    }

    /**
     * Prints a message to inform the user about an error in writing the save.
     */
    public void showWriteSaveError() {
        print(WRITE_SAVE_STRING);
    }

    /**
     * Prints a message to inform the user that a new save is necessary.
     */
    public void showNewSave() {
        print(NEW_SAVE_STRING);
    }
}
