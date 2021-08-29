package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline object.
     *
     * @param description Description of the task.
     * @param by          Date of completion.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatDate() + ")";
    }

    private String formatDate() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}
