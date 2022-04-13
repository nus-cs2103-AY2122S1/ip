package angrybot.task;

/**
 * Encapsulates the representation of a todo task.
 * The task has a description of what the user wants to do.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class ToDo extends Task {

    /**
     * Constructor of a ToDo task.
     *
     * @param description The description of the task to be done.
     * @param isDone      Boolean representing if task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone, 'T');
    }

    /**
     * Compares the task description with another task.
     * Checks time as well. If the other task do not have Date and Time,
     * it is deemed to be "larger" than the task with Date and Time.
     *
     * @param t The task to be compared with.
     * @return -1 if the description for this task is lexicographically smaller
     *         and chronologically later, 0 if same, else 1.
     */
    @Override
    public int compareTo(Task t) {
        if (t instanceof TaskWithDateTime) {
            return 1;
        }
        return super.compareTo(t);
    }
}
