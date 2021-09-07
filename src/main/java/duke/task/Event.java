package duke.task;

import duke.exception.DukeException;

/**
 * Represents events that are tasks taking place at a certain date.
 *
 * @author botr99
 */
public class Event extends DateTask {
    /**
     * Constructs an event with the corresponding description,
     * and a string indicating the date of the event,
     * and that is by default not done.
     *
     * @param description Description of the event.
     * @param at String of the date.
     * @throws DukeException If the at string cannot be parsed into a date.
     */
    public Event(String description, String at) throws DukeException {
        this(description, at, false);
    }

    /**
     * Constructs an event with the corresponding description,
     * and a string indicating the date of the event,
     * and a boolean indicating whether the event is done.
     *
     * @param description Description of the event.
     * @param at String of the date.
     * @param isDone True if event is done; false otherwise.
     * @throws DukeException If the by string cannot be parsed into a date.
     */
    public Event(String description, String at, boolean isDone) throws DukeException {
        super(description, at, isDone);
    }

    /**
     * Returns a string indicating whether an event is done,
     * followed by the description of the event and the
     * formatted date of the event.
     *
     * @return The string representation of an event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatDate() + ")";
    }

    /**
     * Returns a string indicating whether an event is done,
     * followed by the description of the event and the
     * string of the date that was passed in to the constructor.
     *
     * @return The string representation of an event to be stored in storage
     *         under the user's hard disk.
     */
    @Override
    public String toStorageString() {
        return "E | " + super.toStorageString() + " | " + getDateString();
    }
}
