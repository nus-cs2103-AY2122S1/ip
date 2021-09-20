package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline class
     * initializing a Deadline instance with the given task description and deadline.
     *
     * @param description Task description
     * @param by Task deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline changeTime(LocalDate newTiming) {
        return new Deadline(this.description, newTiming);
    }

    @Override
    public String formatForFile() {
        return "D " + super.formatForFile() + " | " + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
    }
}
