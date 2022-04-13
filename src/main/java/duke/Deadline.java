package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class that inherits from Task class.
 * Consists of description and a due date.
 */
public class Deadline extends Task {
    protected String by;

    Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        String date;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(by, formatter).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            date = by;
        }
        this.by = date;
    }


    /**
     * Creates new Deadline task
     * @param description Description of task
     * @param by Due date
     */
    public Deadline(String description, String by) {
        this(description, by, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
