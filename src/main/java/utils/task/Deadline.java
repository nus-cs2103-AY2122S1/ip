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

    private static final DateTimeFormatter SAVE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter SAVE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

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
    public String getTaskFileString(String delimiter, String done, String notDone) {
        return "D" + delimiter + (this.isDone ? done : notDone) + delimiter + this.description + delimiter + date.format(SAVE_DATE_FORMATTER) + " " + time.format(SAVE_TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(dateFormatter) + " " + time.format(timeFormatter) + ")";
    }
}