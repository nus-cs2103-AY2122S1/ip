package duke.task;

import duke.util.CommandModifier;

/**
 * A task class which represents the user's tasks.
 */
public class Task {

    protected String description;
    protected String[] tags;
    protected boolean isDone;

    /**
     * Constructs a task instance which represents the user's task.
     *
     * @param tags The string array consisting all the tags.
     * @param description The task description.
     */
    public Task(String[] tags, String description) {
        this.tags = tags;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Marks a task as done, then show a message telling the user that the task has been marked done successfully.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns a string representation in the format to be written in tasks.txt file.
     *
     * @return The string representation in the format to be written in tasks.txt file.
     */
    public String toDataString() {
        return String.format("| %d | %s| %s", isDone ? 1 : 0, CommandModifier.toTagArrayDataString(tags), description);
    }

    /**
     * Returns a string representation of this task.
     *
     * @return The string representation of this task.
     */
    public String toString() {
        return String.format("%s %s%s", getStatusIcon(), CommandModifier.toTagArrayString(tags), description);
    }
}
