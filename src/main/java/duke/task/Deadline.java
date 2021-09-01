package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Represents a Task need to be done before a specific date/time.
 */
public class Deadline extends Task {

    /**
     * The accepted format of date and time for the deadline
     */
    private final String inputPattern = "dd-MM-yyyy HH:mm";

    /**
     * The accepted format of date and time for the deadline
     */
    private final String outputPattern = "MMM dd yyyy, hh:mm a";

    /**
     * The deadline date and time of this task.
     */
    private LocalDateTime by;

    /**
     * Constructs a Deadline task with task description and deadline.
     *
     * @param description The description of this deadline.
     * @param by          The deadline of this task.
     * @throws DukeException
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (description == "") {
            throw new DukeException("Looks like you forgot to include a description of the deadline.");
        }
        if (by == "") {
            throw new DukeException("Looks like you forgot to include a deadline for the task.");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(inputPattern);
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("The deadline date is invalid. Please follow this format: " + inputPattern);
        }
    }

    /**
     * Returns the deadline date and time as a string to be displayed.
     *
     * @return String representation of the DateTime.
     */
    private String getDateString() {
        return by.format(DateTimeFormatter.ofPattern(outputPattern)).replace("AM", "am").replace("PM", "pm");
    }

    /**
     * Returns a string representation of this task to be displayed.
     *
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateString() + ")";
    }

    /**
     * Returns a string representation of this task to be written into data storage.
     *
     * @param delimiter The delimiter used by the storage to parse data fields.
     * @return The data string representation of this task.
     */
    @Override
    public String toDataString(String delimiter) {
        String tag = "D";
        String done = super.isDone ? "1" : "0";
        return String.join(delimiter, tag, done, super.description,
            by.format(DateTimeFormatter.ofPattern(inputPattern)));
    }
}
