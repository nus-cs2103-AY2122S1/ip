package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * class that extends task
 * labelled with an extra [E] when called
 * stores the date which task should occur
 */
public class Event extends Task {
    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        this.date = Parser.dateParse(at);
    }

    public void changeDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
