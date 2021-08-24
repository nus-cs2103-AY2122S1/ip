package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class IS-A Task.
 *
 * Task that has a start and end time
 * @author Timothy Wong Eu-Jin
 */
public class Event extends Task {

    private LocalDate date;

    public Event(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);

    }

    public LocalDate getDate() {
        return this.date;
    }

    public String toString() {
        return ("[E]" + super.toString() + " (at: " +
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }

}