package utils.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class encapsulates a task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

    private LocalDate date;
    private LocalTime time = LocalTime.parse("00:00:00");

    public Event(String description, String at) {
        super(description);
        String[] datetime = at.split(" ", 2);
        date = LocalDate.parse(datetime[0]);
        if (datetime.length == 2) {
            time = LocalTime.parse(datetime[1]);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(dateFormatter) + " " + time.format(timeFormatter) + ")";
    }
}
