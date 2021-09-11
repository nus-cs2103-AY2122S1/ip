package aisu.task;

import aisu.exception.AisuException;

/**
 * A To-do task.
 * Consists of a description only.
 *
 * @author Liaw Xin Yan
 */
public class Todo extends Task {
    /**
     * Constructor to initialize the To do Task with a description.
     * @param description A writeup of the task details.
     * @throws AisuException if there is no description.
     */
    public Todo(String description) throws AisuException {
        super(description);
    }

    /** {@inheritDoc} */
    @Override
    public String parseData() {
        return "T;;" + (this.isDone ? "1" : "0") + ";;" + this.description + '\n' + this.tagsList.parseData();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format("[To-do] %s %s %s", this.getStatusIcon(), this.description, this.getTags());
    }

}
