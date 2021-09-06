package duke.tasks;

import duke.exceptions.UserInputError;

import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * The Deadline class that represents a task with a starting datetime.
 */
public class Deadline extends Task {

    protected LocalDate when;

    /**
     * Constructor to create a Deadline task.
     *
     * @param description details of task
     * @param when deadline of task
     * @param done Boolean value that indicates completeness of task.
     * @throws UserInputError Throws error with bad user input.
     */
    public Deadline(String description, String when, boolean done) throws UserInputError {
        super(description, Task.Type.DEADLINE, done);
        assert !description.trim().equals("");
        try {
            this.when = getDateFromString(when.trim().toLowerCase());
        } catch (DateTimeException e) {
            throw new UserInputError("Wrong datetime format");
        }
    }

    /**
     * Appends datetime to the String format writeable to Storage file.
     *
     * @return Formatted data as a string
     */
    @Override
    public String convertTaskToString() {
        return super.convertTaskToString() + when;
    }

    @Override
    public String toString() {
        String formattedDate = when.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
