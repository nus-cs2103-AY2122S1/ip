package duke.logic.tasks;

import duke.logic.commands.UpdateCommand.UpdateTaskDescriptor;

/**
 * Represents a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new task.
     *
     * @param description The task description.
     * @param isDone The 'done' status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this(description, false);
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Copies this task, updates the field values as specified, then returns
     * the updated task.
     *
     * @param updateDescriptor The field values to update.
     * @return The updated task.
     */
    public abstract Task createUpdatedCopy(UpdateTaskDescriptor updateDescriptor);

    /**
     * Returns a string representing the status of the task.
     *
     * @return `X` if the task is already done, ` ` if not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done if it is not already done.
     *
     * @return true if the task was not done previously and
     * it has been marked as done, otherwise false.
     */
    public boolean markAsDone() {
        if (!isDone) {
            isDone = true;
            return true;
        }
        return false;
    }

    /**
     * Checks if the task description contains the given keyword.
     * The check is case insensitive.
     *
     * @param keyword The keyword to search for.
     * @return true if the description contains the keyword, false otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns the string representation of the task that will be used
     * to save to file.
     *
     * @return The string representation for file saving purposes.
     */
    public String getSaveFormat() {
        return String.format(" | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
