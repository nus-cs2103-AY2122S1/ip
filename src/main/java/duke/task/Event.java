package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

public class Event extends DateTimeTask {

    /** Code representing a event */
    public final String code = "E";
    /** Date and time of the event */
    private final LocalDateTime at;

    /**
     * Returns a new event.
     *
     * @param input String array containing the description and date and time information of the event.
     * @throws DukeException If there is missing information or the declaration of the task is of the wrong format.
     * @throws DateTimeParseException If the date and time are entered with the wrong format.
     */
    public Event(String[] input) throws DukeException, DateTimeParseException {
        super(input[0]);
        if (input.length != 2) {
            throw new DukeException(DukeException.Type.EVENT);
        }
        this.at = LocalDateTime.parse(input[1]);
    }

    /**
     * Returns a formatted String representation of the event.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[" + code + "]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }

    /**
     * Returns the date and time of the event.
     *
     * @return Date and time of the event.
     */
    @Override
    public LocalDateTime getDateTime() {
        return this.at;
    }

    /**
     * Returns the code of the event.
     *
     * @return The code representing a event.
     */
    @Override
    public String getCode() {
        return this.code;
    }
}
