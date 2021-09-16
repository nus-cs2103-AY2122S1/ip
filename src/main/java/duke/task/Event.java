package duke.task;

import duke.main.DukeException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Encapsulates an event with a time
 */
public class Event extends Task {

    private LocalDate at;

    /**
     * Constructor for Event.
     *
     * @param description of Event.
     * @param at          Time for Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = Task.parseTime(at);
    }

    /**
     * Constructor for an Event.
     *
     * @param descAndTime String containing description and time
     * @throws DukeException If extraction fails.
     */
    public Event(String descAndTime) throws DukeException {
        this(extractDesc(descAndTime), extractTime(descAndTime));
    }

    /**
     * @param desc        Description of Event.
     * @param at          Time for Event.
     * @param isCompleted indicates whether event is completed.
     * @param tags        List of tags associated with the Event.
     */
    public Event(String desc, String at, boolean isCompleted, List<String> tags) {
        this(desc, at);
        super.isComplete = isCompleted;
    }

    private static String extractDesc(String descAndTime) throws DukeException {
        if (descAndTime.equals("")) {
            throw new DukeException("\\u2639 OOPS!!! Your event needs a description.\n");
        }
        return descAndTime.split(" at ")[0];
    }

    private static String extractTime(String descAndTime) throws DukeException {
        try {
            return descAndTime.split(" at ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! You need to specify a time.\n");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "\n\t(at: " + Task.printTime(at) + ")\n";
    }

    /**
     * Generates a formatted String for storing Event.
     *
     * @return formatted String for storing Event.
     */
    @Override
    public String generateStorageString() {
        return "E | " + super.isComplete + " | " + super.description + " |" + super.formatTags() + " | " + this.at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Event event = (Event) o;
        return at.equals(event.at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), at);
    }
}
