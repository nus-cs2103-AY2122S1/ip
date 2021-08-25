package duke.tasks;

import duke.exceptions.DukeException;
import duke.functionality.Datetime;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final Datetime dueDate;
    private static final String TASK_TAG = "deadline";

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = new Datetime(dueDate);
    }

    public Deadline(String taskName, String dueDate, boolean isDone) {
        super(taskName, isDone);
        this.dueDate = new Datetime(dueDate);
    }

    /**
     * Returns the string format in which this task is to be saved within a file.
     *
     * @return String representation of task for saving within a file.
     */
    public String fileSaveFormat() {
        return String.format("D | %d | %s | %s", this.isDone() ? 1 : 0, this.taskName(),
                this.dueDate.getDatetimeString());
    }

    public static String taskTag() {
        return TASK_TAG;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate.toString() + ")";
    }
}
