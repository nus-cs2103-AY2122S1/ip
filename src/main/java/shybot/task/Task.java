package shybot.task;

import java.util.Arrays;

import shybot.exception.ShyBotException;
import shybot.exception.ShyBotIllegalFormatException;

/**
 * Task class encapsulates a task.
 */
public abstract class Task {
    /**
     * Description of the task.
     */
    protected String description;
    /**
     * True if the task is done.
     */
    protected boolean isDone;
    /**
     * Tags of the tasks.
     */
    protected String[] tags;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description Description of the task.
     * @throws ShyBotException If the description does not follow the format.
     */
    public Task(String description, String[] tags) throws ShyBotException {
        if (description.isEmpty()) {
            throw new ShyBotIllegalFormatException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        this.description = description;
        this.tags = tags;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     * Note it does not update the db.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


    /**
     * Returns the string representation of the task to be saved in the db.
     *
     * @return String representation of the task to be saved in the db.
     */
    public String toDataString() {
        return " | " + (this.getStatusIcon().equals("✔️") ? '1' : '0') + " | " + this.description + " | " + String
            .join(" ", this.tags);
    }

    /**
     * Returns true if this task contains the keyword.
     *
     * @param keyword Keyword.
     * @return true if this task contains the keyoword.
     */
    public boolean contains(String keyword) {
        // TODO: contain partially
        return this.description.contains(keyword) || Arrays.asList(this.tags).contains(keyword);
    }

    private String getStatusIcon() {
        return isDone ? "✔️" : "⚔️";
    }
}
