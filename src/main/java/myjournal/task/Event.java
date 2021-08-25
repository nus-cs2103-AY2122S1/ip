package myjournal.task;

import myjournal.task.Task;

/**
 * Creates tasks with the type of event.
 *
 * @author Felissa Faustine
 */
public class Event extends Task {
    public String time;

    /**
     * Constructs the Event object.
     *
     * @param taskName The name of the task.
     * @param time The time of the event.
     */
    public Event(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    /**
     * Returns the time of the event task.
     *
     * @return The time of the event task.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Gets the symbol of event.
     *
     * @return The string representation of the symbol of event.
     */
    public String getSymbol() {
        return "E";
    }

    /**
     * Gets the string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[" + getSymbol() + "]" + super.toString() + "(at:" + this.time + ")";
    }
}
