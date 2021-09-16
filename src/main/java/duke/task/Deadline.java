package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Class that encapsulates Deadline tasks.
 */
public class Deadline extends Task {

    protected String deadline;
    protected String date = "";

    /**
     * Constructor for Deadline.
     *
     * @param description Task description.
     * @param deadline Date of deadline.
     * @throws DukeException If user input is not valid.
     */
    public Deadline(String description, String deadline) throws DukeException {
        super(description);

        try {
            LocalDate localDate = LocalDate.parse(deadline);
            this.date = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Deadline should be in a yyyy-mm-dd format.");
        }

        if (description.isEmpty() || description == "" || description == " ") {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            this.description = description;
        }

        if (deadline.isEmpty() || deadline == "" || deadline == " ") {
            throw new DukeException("☹ OOPS!!! The deadline of this task must be indicated.");
        } else {
            this.deadline = this.date;
        }
    }

    /**
     * Returns a String representation of the Deadline task.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + deadline + ")";
    }

    /**
     * Returns a String representation of the date of the deadline.
     *
     * @return A String representation of the date of the deadline.
     */
    @Override
    public String additionalDates() {
        return this.deadline;
    }
}
