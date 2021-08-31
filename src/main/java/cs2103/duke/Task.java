package cs2103.duke;

/**
 * This class encapsulates a Task object.
 */

public class Task {
    protected int index;
    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected String reminder;

    /**
     * Creates a task with the specified description, isDone false by default.
     *
     * @param index       Index of the task, starting from 0.
     * @param description description of task
     */
    public Task(int index, String description) {
        this.index = index;
        this.description = description;
        this.isDone = false;
    }

    public int getIndex() {
        return index;
    }

    /**
     * Getter method for status using isDone
     *
     * @return an icon 'X' if complete, space if otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Getter method for type of task using taskType.
     *
     * @return an icon representing the type of the task (T, D, E)
     */
    public String getTypeIcon() {
        return (this.taskType == "todo" ? "T" :
                this.taskType == "deadline" ? "D" :
                        this.taskType == "event" ? "E" : "NONE");
    }

    /**
     * Getter method for task reminder
     *
     * @return the string reminder for the task
     */
    public String getReminder() {
        return this.reminder;
    }

    /**
     * Setter method for isDone of a Task object
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns string representation of Task.
     *
     * @return string representation of Task
     */
    public String toString() {
        return ("[" + getStatusIcon() + "]" + description);
    }
}
