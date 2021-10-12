package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * class that extends task
 * labelled with an extra [D] when called
 * stores the date which task should be done by
 */
public class Deadline extends Task {
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        date = Parser.dateParse(by);
    }

    public void changeDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
