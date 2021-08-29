package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.NoDescriptionException;

/**
 * The Deadline class that represents a task with a starting datetime.
 */
public class Deadline extends Task {

    protected LocalDate when;

    /**
     * Constructor to create a Dedline task.
     *
     * @param description details of task
     * @param when deadline of task
     * @param done Boolean value that indicates completeness of task.
     * @throws NoDescriptionException
     */
    public Deadline(String description, String when, boolean done) throws NoDescriptionException {
        super(description, Task.Type.DEADLINE, done);
        try {
            this.when = LocalDate.parse(when.trim());
        } catch (DateTimeException e) {
            throw new NoDescriptionException("Wrong datetime format");
        }
    }

    /**
     * Appends datetime to the String format writeable to Storage file.
     *
     * @return Formatted data as a string
     */
    @Override
    public String taskToString() {
        return super.taskToString() + when;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + when.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
