package lebron.task;

import lebron.exception.LebronException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents the Events event.
 *
 * @author Nigel Tan
 */
public class Events extends Task {

    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructor.
     *
     * @param description the name of the task.
     * @param date the date of the start of the event.
     * @param time the time of the start of the event.
     */
    public Events(String description, String date, String time) throws LebronException {
        super(description);
        try {
            this.date = LocalDate.parse(date);
            this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));
        } catch (DateTimeParseException e) {
            throw new LebronException("    :( OOPS! Please check that your date and time is " +
                    "valid and formatted as 'yyyy-MM-dd' 'HHmm'.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " " + time.format(DateTimeFormatter.ofPattern("HHmm"))+ ")";
    }

    /**
     * Produce the desired format for storing to file.
     *
     * @return the desired string.
     */
    @Override
    public String getStringForFile() {
        return "E | " + super.getDoneValue() + " | " + super.getName() + " | " + this.date + " " + this.time;
    }
}
