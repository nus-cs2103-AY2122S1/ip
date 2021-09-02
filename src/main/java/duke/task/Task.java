package duke.task;

import java.time.LocalDate;

/**
 * Represents a task containing a description and a done status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * Creates a Task containing a description that is by default undone.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        // Asserts that task is not being created with empty string or null as description parameter.
        assert description != null : "description string cannot be null.";
        assert !description.equals("") : "description string cannot be empty";

        this.description = description;
        this.isDone = false;
    }

    /**
     * Indicates whether this Task is done.
     *
     * @return boolean indicating whether this Task is done.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns description of this Task.
     *
     * @return Description contained in this Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns "X" if this Task is done and " " otherwise.
     *
     * @return A formatted String indicating if Task is done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns null.
     *
     * @return null.
     */
    public LocalDate getTime() {
        return null;
    }

    /**
     * Marks this Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns null.
     *
     * @return null.
     */
    public String getTaskType() {
        return null;
    }

    /**
     * Returns a formatted string that begins with this Task's done status and ends with its description.
     * The String is formatted to fit the form "[S] D", where S is either "X" or " " depending on this Task's
     * done status and D is this Task's description.
     *
     * @return Formatted description of this Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Indicates whether another object is equals to this Task.
     *
     * @param obj Other object to be compared to.
     * @return A boolean indicating whether the other object is equals to this Task.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task other = (Task) obj;

            // Check is done status and description are the same.
            boolean isDoneStatusSame = this.isDone == other.isDone;
            boolean isDescriptionSame = this.description.equals(other.description);

            return (isDoneStatusSame && isDescriptionSame);
        }
        return false;
    }
}
