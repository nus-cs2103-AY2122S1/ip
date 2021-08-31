package cs2103.duke;

/**
 * This class encapsulates a Task object.
 */

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected String reminder;

    /**
     * Creates a task with the specified description, isDone is false by default.
     *
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter method for status using isDone.
     *
     * @return A letter 'X' if task is complete, an empty space if otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Getter method for type of task using taskType.
     *
     * @return A letter representing the type of the task (T, D, E).
     */
    public String getTypeIcon() {
        return (this.taskType == "todo" ? "T" :
                this.taskType == "deadline" ? "D" :
                        this.taskType == "event" ? "E" : "NONE");
    }

    /**
     * Getter method for task reminder.
     *
     * @return The string reminder for the task.
     */
    public String getReminder() {
        return this.reminder;
    }

    /**
     * Setter method for isDone of a Task object.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns string representation of Task.
     *
     * @return String representation of Task.
     */
    public String toString() {
        return ("[" + getStatusIcon() + "]" + description);
    }
}
