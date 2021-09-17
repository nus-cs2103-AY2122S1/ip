package duke.task;

/**
 * The Deadline class encapsulates a Task to be done by a given deadline.
 */
public class Deadline extends TimeTask {

    public static final String COMMAND_WORD = "deadline";
    public static final String TASK_INTIAL = "D";
    public static final String TIME_PREFIX = "by";
    public static final String KEYWORD = "/by";
    public static final String KEYWORD_WITH_SPACE = KEYWORD + " ";

    /**
     * Constructor for a Deadline task.
     *
     * @param name the given name of the Deadline.
     * @param dline the given deadline of the Deadline.
     */
    public Deadline(String name, String dline) {
        super(name, dline);
    }

    /**
     * Returns a String representing the Deadline Task.
     *
     * @return a String representing the Deadline Task.
     */
    @Override
    public String toString() {
        return generateToString(TASK_INTIAL, TIME_PREFIX);
    }

    /**
     * Returns a String representing the Deadline Task to be saved in the taskList.txt file.
     *
     * @return a String representing the Deadline Task to be saved in the taskList.txt file.
     */
    public String printToFile() {
        return generatePrintToFile(TASK_INTIAL);
    }
}
