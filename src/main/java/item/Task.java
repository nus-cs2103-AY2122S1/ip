package item;

import exception.BotException;
import exception.EmptyCommandException;

/**
 * Task contains items which can be done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
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
     * Mark the Task as Done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Return the string representation of Task.
     * @return The string representation of Task, including status and description.
     */
    @Override
    public String toString() {
        String s = String.format("[%s] %s", this.getStatusIcon(), this.description);
        return s;
    }
}