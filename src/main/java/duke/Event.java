package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an event, which can be entered into the to-do-list.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Constructor for an event task.
     *
     * @param description A short description of the event.
     * @param at The time of the event.
     */
    public Event(String description, String at) {
        super(description);
        LocalDate d = LocalDate.parse(at);
        this.at = d;
    }

    /**
     * Constructor for an event task.
     *
     * @param description A short description of the event.
     * @param isDone A boolean to indicate whether the event is already done.
     * @param at The time of the event.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        LocalDate d = LocalDate.parse(at);
        this.at = d;
    }

    /**
     * Constructor for an event task.
     *
     * @param description A short description of the event.
     * @param isDone A boolean to indicate whether the event is already done.
     * @param tag The tag to attach to the task.
     * @param at The time of the event.
     */
    public Event(String description, boolean isDone, String tag, String at) {
        super(description, isDone, tag);
        LocalDate d = LocalDate.parse(at);
        this.at = d;
    }

    /**
     * Returns the string representation of the task, in a format suitable for storing in a text file.
     *
     * @return The string representation of the task, in a format suitable for storing in a text file.
     */
    @Override
    public String toStringForFile() {
        return "E | " + super.toStringForFile() + " | " + this.at;
    }

    @Override
    public String toString() {
        String icon = "[E]";
        String datetime = this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return icon + super.toString() + " (at: " + datetime + ")";
    }
}
