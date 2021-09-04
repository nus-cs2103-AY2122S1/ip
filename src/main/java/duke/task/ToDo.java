package duke.task;

/**
 * The ToDo class encapsulates an item to be done.
 */
public class ToDo extends Task {

    public static final String COMMAND_WORD = "todo";
    public static final String TASK_INITIAL = "T";

    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a String representing the ToDo Task.
     *
     * @return a String representing the ToDo Task.
     */
    @Override
    public String toString() {
        return generateToString(TASK_INITIAL);
    }

    /**
     * Returns a String representing the ToDo Task to be saved in the taskList.txt file.
     *
     * @return a String representing the ToDo Task to be saved in the taskList.txt file.
     */
    public String printToFile() {
        return generatePrintToFile(TASK_INITIAL);
    }
}
