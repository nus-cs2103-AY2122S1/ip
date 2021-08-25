package duke.task;

import duke.main.DukeException;
import duke.main.Parser;

import java.time.LocalDate;
import java.util.Objects;

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
        this.at = Parser.parseTime(at);
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
     */
    public Event(String desc, String at, boolean isCompleted) {
        this(desc, at);
        super.completed = isCompleted;
    }

    private static String extractDesc(String descAndTime) throws DukeException {
        if (descAndTime.equals("")) {
            throw new DukeException("\t☹ OOPS!!! Your event needs a description.\n");
        }
        return descAndTime.split(" at ")[0];
    }

    private static String extractTime(String descAndTime) throws DukeException {
        try {
            return descAndTime.split(" at ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\t☹ OOPS!!! You need to specify a time.\n");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Task.printTime(at) + ")";
    }

    /**
     * Generates a formatted String for storing Event.
     *
     * @return formatted String for storing Event.
     */
    @Override
    public String storageString() {
        return "E | " + super.completed + " | " + super.description + " | " + this.at;
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
