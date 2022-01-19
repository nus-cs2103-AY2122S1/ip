package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/** A class that represents an event task */
public class Event extends Task {
    private LocalDate at;

    /**
     * Constructs Event object with description and date.
     *
     * @param description The description.
     * @param at The event date.
     * @throws DukeException If the date is invalid.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format: " + at);
        }
    }

    /**
     * Constructs Event object with description, date and done state.
     *
     * @param description The description.
     * @param at The event date.
     * @param isDone The state whether the task is done.
     * @throws DukeException If the date is invalid.
     */
    public Event(String description, String at, boolean isDone) throws DukeException {
        super(description, isDone);

        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format: " + at);
        }
    }

    /**
     * Constructs Event object with description, priority, date and done state.
     *
     * @param description The description.
     * @param priority The priority.
     * @param at The event date.
     * @param isDone The state whether the task is done.
     * @throws DukeException If the date or priority is invalid.
     */
    public Event(String description, Priority priority, String at, boolean isDone) throws DukeException {
        super(description, priority, isDone);

        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format: " + at);
        }
    }

    @Override
    public String serialize() {
        return String.join(" | ", "E", this.priority.toString(),
                this.isDone ? "1" : "0", this.description, this.at.toString());
    }

    @Override
    public String toString() {
        String date = this.at.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
