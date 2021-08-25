package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.NoDescriptionException;

public class Deadline extends Task {

    protected LocalDate when;

    public Deadline(String description, String when, boolean done) throws NoDescriptionException {
        super(description, Task.Type.DEADLINE, done);
        try {
            this.when = LocalDate.parse(when.trim());
        } catch (DateTimeException e) {
            throw new NoDescriptionException("Wrong datetime format");
        }
    }

    @Override
    public String taskToString() {
        return super.taskToString() + when;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + when.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
