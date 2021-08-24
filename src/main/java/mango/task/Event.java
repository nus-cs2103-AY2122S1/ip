package mango.task;

import mango.DukeException;

/**
 * Represents a task that is an event. An <code>Event</code> object corresponds to a
 * <code>Task</code> that has a description, a completion status, and a date on which it happens.
 */
public class Event extends Task {
    protected String date;

    /**
     * Constructor for an Event.
     *
     * @param description The description of the event.
     * @param date The date on which the event occurs.
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Constructor for an Event, initialised with a completion status.
     *
     * @param description The description of the event.
     * @param date The date on which the event occurs.
     * @param status The completion status of the event.
     */
    public Event(String description, String date, String status) {
        super(description, status.equals("true"));
        this.date = date;
    }

    /**
     * Returns the string representation of the type of task.
     *
     * @return The string representation of the type of task.
     */
    public String getType() {
        return "E";
    }

    /**
     * Checks if the input string array is valid.
     *
     * @param arr The input string array.
     * @return True if the string array is valid, else false.
     * @throws DukeException If the array length is 1.
     */
    public static boolean isValid(String[] arr) throws DukeException {
        if (arr.length == 1) {
            throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
        }

        return true;
    }

    /**
     * Returns the description of the event.
     *
     * @return The description of the event.
     */
    public String getDescription() {
        return String.format("%s (at: %s)", this.description, this.date);
    }

    /**
     * Returns the string representation of the event.
     *
     * @return The string representation of the event.
     */
    public String save() {
        return String.format("%s:%s:%s:%s\n", this.getType(), this.getStatus(), this.description, this.date);
    }
}
