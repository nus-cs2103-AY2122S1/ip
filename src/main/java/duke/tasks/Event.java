package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructs a Event object.
     *
     * @param description Event description
     * @param date Date
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.time = null;
    }

    /**
     * Constructs a Event object.
     *
     * @param description Event description
     * @param date Date
     * @param time Time
     */
    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        String dateString = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (this.time == null) {
            return "[E]" + super.toString() + " (at: " + dateString + ")";
        } else {
            String timeString = this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
            return "[E]" + super.toString() + " (at: " + dateString + " " + timeString + ")";
        }
    }

    @Override
    public String toSaveString() {
        String dateString = this.date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        if (this.time == null) {
            return "| E | " + super.toSaveString() + " | " + dateString;
        } else {
            String timeString = this.time.format(DateTimeFormatter.ofPattern("HH:mm"));
            return "| E | " + super.toSaveString() + " | " + dateString + " | " + timeString;
        }
    }
}
