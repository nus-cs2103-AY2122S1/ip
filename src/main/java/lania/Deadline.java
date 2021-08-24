package lania;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /** lania.Deadline of a task */
    protected String by;
    protected LocalDateTime dateTime;

    /**
     * Constructor for lania.Deadline. Takes in a String description and by.
     * Initialises description and deadline of task.
     *
     * @param description The name of the deadline.
     * @param by lania.Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.dateTime = LocalDateTime.parse(by, formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy ha")) + ")";
    }
}
