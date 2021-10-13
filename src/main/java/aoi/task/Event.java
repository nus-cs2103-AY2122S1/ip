package aoi.task;

import java.time.LocalDateTime;

import aoi.parser.Parser;

/**
 * Encapsulates an Event object that implements a Task and has an event time.
 *
 * @author Owen Tan
 * @version aoi.Aoi Level-9
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Constructor for Event.
     *
     * @param description Description to be stored in an Event.
     * @param at Event Time.
     */
    public Event(String description, LocalDateTime at) {
        this(description, false, at, "");
    }

    /**
     * Constructor for Event.
     *
     * @param description Description to be stored in an Event.
     * @param isDone Boolean that represents Task completion status.
     * @param at Event Time.
     */
    public Event(String description, boolean isDone, LocalDateTime at) {
        this(description, isDone, at, "");
    }

    /**
     * Constructor for Event.
     *
     * @param description Description to be stored in an Event.
     * @param at Event Time.
     * @param notes A string representation of notes.
     */
    public Event(String description, LocalDateTime at, String notes) {
        this(description, false, at, notes);
    }

    /**
     * Constructor for Event.
     *
     * @param description Description to be stored in an Event.
     * @param isDone Boolean that represents Task completion status.
     * @param at Event Time.
     * @param notes A string representation of notes.
     */
    public Event(String description, boolean isDone, LocalDateTime at, String notes) {
        super(description, isDone, notes);
        this.at = at;
    }

    /**
     * Returns a string representation of Event.
     *
     * @return A string representation of Event.
     */
    @Override
    public String toString() {
        String dateString = at.format(format);
        String str = "[E]" + super.toString() + " (at: " + dateString + ")";
        if (!notes.equals("")) {
            str += "\n  Notes: " + notes;
        }
        return str;
    }

    /**
     * Returns a string formatted for saving purposes.
     *
     * @return A string representation of Event for saving.
     */
    @Override
    public String printInSaveFormat() {
        String dateString = at.format(Parser.FORMAT);
        String[] info = {"E", isDone ? "1" : "0", description, dateString, notes};
        return String.join(" | ", info);
    }
}
