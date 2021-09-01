package duke.task;

import duke.DukeException;

/**
 * Represents a Task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    /**
     * The time at which the event occurs.
     */
    protected String at;

    /**
     * Constructs an Event task with task description and time of event.
     *
     * @param description The description of this event.
     * @param at          The time of this event.
     * @throws DukeException
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        if (description == "") {
            throw new DukeException("Looks like you forgot to include a description of the event.");
        }
        if (at == "") {
            throw new DukeException("Looks like you forgot to include when the event is at.");
        }
        this.at = at;
    }

    /**
     * Returns a string representation of this task to be displayed.
     *
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns a string representation of this task to be written into data storage.
     *
     * @param delimiter The delimiter used by the storage to parse data fields.
     * @return The data string representation of this task.
     */
    @Override
    public String toDataString(String delimiter) {
        String tag = "E";
        String done = super.isDone ? "1" : "0";
        return String.join(delimiter, tag, done, super.description, at);
    }
}
