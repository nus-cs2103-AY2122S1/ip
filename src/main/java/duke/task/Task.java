package duke.task;

import java.util.List;
import java.util.ArrayList;

/**
 * The abstract Task class encapsulates information
 * and methods pertaining to Tasks in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public abstract class Task {
    /* A description of this Task */
    protected String description;

    /* A boolean value pertaining to if this task has been done */
    protected boolean isDone;
    
    /* A String value denoting the type of Task */
    protected String type;

    /**
     * Creates and initalizes a new Task with the description.
     *
     * @param description The description of the task.
     * @return A new Task object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the String representing this task.
     *
     * @return Returns the type of this Task.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the description of this task.
     *
     * @return Returns the description of this Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns the format a user should use to creating this task with Duke.
     *
     * @return Returns the format a user should use to creating this task with Duke.
     */
    public abstract String getFormat();

    /**
     * Returns the list of parameters used to represent this task.
     *
     * @return Returns a list of parameters.
     */
    public List<String> getSaveParameters() {
        List<String> params = new ArrayList<>();
        params.add(type.substring(0, 1));
        params.add(isDone ? "1" : "0");
        params.add(description);
        return params;
    }

    /**
     * The String representation of this Task object.
     *
     * @return Returns the String representation of this Task object.
     */
    public String toString() {
        return String.format("[%c][%s] %s", type.charAt(0), (isDone ? "X" : " "), description);
    }
}
