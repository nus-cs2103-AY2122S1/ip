package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of the Task Object.
 * Includes a deadline date to complete task by.
 */
public class Deadline extends Task {

    protected LocalDate date;

    /**
     * A constructor without boolean isDone.
     */
    public Deadline(String description, String by) {
        super(description);
        this.date = LocalDate.parse(by);
    }

    /**
     * A constructor with boolean isDone.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.date = LocalDate.parse(by);
    }

    @Override
    public String toString() {

        return "D " + super.toString() + " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
