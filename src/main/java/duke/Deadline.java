package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate time;

    /**
     * Initializes a Deadline object
     * @param description    The description of the task
     * @param by             The date it is due
     */
    public Deadline(String description, String by) {
        super(description);
        this.label = "D";
        time = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
