package duke.task;

import java.util.List;

/**
 * The Event class encapsulates information
 * and methods pertaining to Event tasks.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class Event extends Task {
    private String at;

    /**
     * Creates and initalizes a new Event task with the parameters.
     *
     * @param description The description of the task.
     * @param at The string representing where/when the event is at.
     * @return A new Event object.
     */
    public Event(String description, String at) {
        super(description);
        this.type = "Event";
        this.at = at;
    }

    /**
     * Returns the format a user should use to creating this task with Duke.
     *
     * @return Returns the format a user should use to creating this task with Duke.
     */
    public String getFormat() {
        return "event <description> /at <event period>";
    }

    /**
     * Returns the list of parameters used to represent this task.
     *
     * @return Returns a list of parameters.
     */
    public List<String> getSaveParameters() {
        List<String> params = super.getSaveParameters();
        params.add(at);
        return params;
    }

    /**
     * The String representation of this Task object.
     *
     * @return Returns the String representation of this Task object.
     */
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}