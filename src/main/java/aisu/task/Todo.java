package aisu.task;

import aisu.exception.AisuException;

/**
 * A To-do task.
 * Consists of a description only.
 *
 * @author Liaw Xin Yan
 */
public class Todo extends Task {
    public Todo(String description) throws AisuException {
        super(description);
    }

    /**
     * Parses data in readable format to be stored in storage.
     *
     * @return Parsed data.
     */
    @Override
    public String parseData() {
        return "T;;" + (this.isDone ? "1" : "0") + ";;" + this.description;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return The Task in readable format.
     */
    @Override
    public String toString() {
        return String.format("[To-do] %s %s", this.getStatusIcon(), this.description);
    }

}
