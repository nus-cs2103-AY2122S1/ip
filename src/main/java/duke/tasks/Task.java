package duke.tasks;

import java.time.LocalDate;

/**
 * Abstract class representing a task that can be completed.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task using only description, isDone is false by default.
     *
     * @param description A description of the task to be done
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task given both description and completion flag.
     *
     * @param description A description of the task to be done
     * @param isDone      Tracks whether the task is complete or not
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a string representing whether the task is complete.
     *
     * @return Either the letter "X" or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the status of completion of the Task.
     *
     * @return True if task is marked as complete
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a formatted version with delimiters of this task for saving to file.
     *
     * @return A formatted String representing the data stored in the task
     */
    public String getFormattedData() {
        return this.getTaskIdentifier() + "|" + (this.isDone ? 1 : 0) + "|" + this.description;
    }

    /**
     * Abstract function for each Task to return an identifier of what type of task it is.
     *
     * @return A character identifying the type of Task it is
     */
    public abstract String getTaskIdentifier();

    /**
     * Returns true (false by default) if the Task has a due date that is due on the given date.
     *
     * @param dueDate The date to check if this task is on the same deadline/due date
     * @return Always returns false by default unless overridden
     */
    public boolean isDue(LocalDate dueDate) {
        return false;
    }

    /**
     * Returns true if the Task description contains the given keyword/String
     *
     * @param keyword A String to be checked if it is contained in the Task description field
     * @return True iff this.description contains the given keyword String
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns a string representation of the Todo Task
     *
     * @return String representing the Todo Task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Does a deep comparison of this object to another object.
     *
     * @param otherObj The other object to be compared to
     * @return Returns true iff the two objects are of same type and same value in every field
     */
    @Override
    public boolean equals(Object otherObj) {
        if (!(otherObj instanceof Task)) {
            return false;
        } else {
            final Task otherTask = (Task) otherObj;

            if (this.isDone != otherTask.isDone) {
                return false;
            } else {
                return this.description.equals(otherTask.description);
            }
        }
    }
}
