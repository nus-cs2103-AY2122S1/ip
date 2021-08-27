package utils.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class encapsulates a task that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

    private LocalDate date;
    private LocalTime time = LocalTime.parse("00:00");

    public Deadline(String description, String by) {
        super(description);
        String[] datetime = by.split(" ", 2);
        date = LocalDate.parse(datetime[0]);
        if (datetime.length == 2) {
            time = LocalTime.parse(datetime[1]);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(dateFormatter) + " " + time.format(timeFormatter) + ")";
    }
}