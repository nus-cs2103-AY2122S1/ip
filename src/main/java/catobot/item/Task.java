package catobot.item;

import catobot.exception.EmptyCommandException;

/**
 * Represents items which can be done.
 */
public class Task {
    /** Details of the task. */
    private final String description;
    /** Status of whether the task is done. */
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description The task description.
     * @throws EmptyCommandException if the description is empty.
     */
    public Task(String description) throws EmptyCommandException {
        if (description.isEmpty()) {
            throw new EmptyCommandException("task");
        }
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the Task as Done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Checks if the task contains a keyword.
     *
     * @param keyword The keyword to search for.
     * @return True if the task description contains the keyword, false otherwise.
     */
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Represents Task in the format of string.
     *
     * @return The string representation of Task, including status and description.
     */
    @Override
    public String toString() {
        String s = String.format("[%s] %s", this.getStatusIcon(), this.description);
        return s;
    }

    /**
     * Represents the format of task in storage.
     *
     * @return The string representation of task in storage.
     */
    protected String toStringInDoc() {
        String s = String.format("%s | %s", this.isDone ? 1 : 0, this.description);
        return s;
    }
}