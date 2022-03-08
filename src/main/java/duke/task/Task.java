package duke.task;

import java.util.Date;

/**
 * This class encapsulates a task that Duke can handle.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public abstract class Task implements Comparable<Task> {
    private final String taskName;
    private boolean isDone;

    /**
     * Instantiates a new task.
     *
     * @param taskName description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     **/
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Checks the completion status of the task.
     *
     * @return true if task is complete, false otherwise,
     */
    public boolean isComplete() {
        return isDone;
    }

    /**
     * Gets the String representation of the task's status.
     *
     * @return "X" if completed and "" otherwise
     */
    private String getIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Gets the name of the task.
     *
     * @return String representation of the task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Gets the date of the task.
     *
     * @return Task date.
     */
    public abstract Date getDate();

    /**
     * Converts task to the suitable format to be saved in storage file.
     *
     * @return Reformatted string representation of a task in the storage file.
     */
    public String formatToSave() {
        return String.format("%d | %s", isDone ? 1 : 0, taskName);
    }

    /**
     * String representation of a task
     *
     * @return string representation of a task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getIcon(), taskName);
    }

    /**
     * Compares this task to another task by date.
     * A task without a date is smaller than a task with a date.
     * A completed task is smaller than an incomplete task.
     * A task with an earlier date is smaller than a task with a later date.
     *
     * @param t The other task to be compared to.
     * @return -1 if this task is smaller,
     * -0 if the tasks are equal in priority,
     * 1 if this task is larger.
     */
    @Override
    public int compareTo(Task t) {
        if (!this.isComplete() | !t.isComplete()) {
            if (this.isComplete()) {
                return -1;
            } else if (t.isComplete()) {
                return 1;
            }
        }
        Date thisDate = this.getDate();
        Date otherDate = t.getDate();
        if (thisDate == null && otherDate == null) {
            return 0;
        } else if (thisDate == null) {
            return -1;
        } else if (otherDate == null) {
            return 1;
        }
        return thisDate.compareTo(otherDate);
    }
}
