package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

public class Deadline extends DateTimeTask {

    /** Code representing a deadline task */
    public final String code = "D";
    /** Date and time of the deadline */
    private final LocalDateTime by;

    /**
     * Returns a new deadline task.
     *
     * @param input String array containing the description and date and time information of the task.
     * @throws DukeException If there is missing information or the declaration of the task is of the wrong format.
     * @throws DateTimeParseException If the date and time are entered with the wrong format.
     */
    public Deadline(String[] input) throws DukeException, DateTimeParseException {
        super(input[0]);
        if (input.length != 2) {
            throw new DukeException(DukeException.Type.DEADLINE);
        }
        this.by = LocalDateTime.parse(input[1]);
    }

    /**
     * Returns a formatted String representation of the deadline.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[" + code + "]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }

    /**
     * Returns the date and time of the deadline.
     *
     * @return Date and time of the deadline.
     */
    @Override
    public LocalDateTime getDateTime() {
        return this.by;
    }

    /**
     * Returns the code of the deadline task.
     *
     * @return The code representing a deadline.
     */
    @Override
    public String getCode() {
        return this.code;
    }
}
