package angrybot.task;

import java.time.LocalDateTime;

/**
 * Encapsulate the representation of a task with date and time.
 * The task has a description of what the task is about,
 * and a boolean to store if the task is done.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */

public class TaskWithDateTime extends Task {
    protected final LocalDateTime dateTime;

    /**
     * Constructor for a task.
     *
     * @param description    The description of the task.
     * @param isDone         Boolean representing if task is done.
     * @param representation The type of task.
     * @param dt             The Date and Time for the task.
     */
    public TaskWithDateTime(String description, boolean isDone, char representation, LocalDateTime dt) {
        super(description, isDone, representation);
        this.dateTime = dt;
    }

    private int compareDateTime(TaskWithDateTime t) {
        return this.dateTime.compareTo(t.dateTime);
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
            TaskWithDateTime t1 = (TaskWithDateTime) t;
            if (this.compareDateTime(t1) <= 0) {
                return super.compareTo(t);
            }
            return 1;
        }
        return -1;
    }
}
