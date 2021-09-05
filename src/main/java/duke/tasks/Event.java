package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.UserInputError;

/**
 * The Event class that represents a task with a starting datetime.
 */
public class Event extends Task {

    protected LocalDate when;

    /**
     * Constructor to create an Event task.
     *
     * @param description details of task
     * @param when deadline of task
     * @param done Boolean value that indicates completeness of task.
     * @throws UserInputError Throws error with bad user input.
     */
    public Event(String description, String when, boolean done) throws UserInputError {
        super(description, Task.Type.EVENT, done);
        assert !description.trim().equals("");
        try {
            this.when = LocalDate.parse(when.trim());
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
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }
}
