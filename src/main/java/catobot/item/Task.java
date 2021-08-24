package catobot.item;

import catobot.exception.EmptyCommandException;

/**
 * Task contains items which can be done.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task.
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
     * Check if the task contains a keyword.
     *
     * @param keyword The keyword to search for.
     * @return True if the task description contains the keyword, false otherwise.
     */
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns the string representation of Task.
     *
     * @return The string representation of Task, including status and description.
     */
    @Override
    public String toString() {
        String s = String.format("[%s] %s", this.getStatusIcon(), this.description);
        return s;
    }

    public String toStringInDoc() {
        String s = String.format("%s | %s", this.isDone ? 1 : 0, this.description);
        return s;
    }
}