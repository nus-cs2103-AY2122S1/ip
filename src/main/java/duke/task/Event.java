package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates Event objects.
 *
 * @author Leong Hong Fai
 */
public class Event extends Task {
    private LocalDate date;

    public Event(String name, String date) {
        super(name);
        this.date = LocalDate.parse(date);
    }

    /**
     * Represents Event in a String format
     *
     * @return A string consisting of the information of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}