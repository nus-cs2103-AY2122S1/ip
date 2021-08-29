package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    protected LocalTime time;
    protected LocalDate date;

    /**
     * Public constructor to initialize an Event object.
     *
     * @param description The description of the event.
     * @param time The time of the event.
     * @param date The date of the event.
     */
    public Event(String description, LocalTime time, LocalDate date) {
        super(description);
        this.time = time;
        this.date = date;
    }

    @Override
    public String outputFormat() {
        return "E" + super.outputFormat() + " | " + date.getDayOfMonth() + "/" + date.getMonthValue()
                + "/" + date.getYear()
                + " " + String.format("%1$" + 2 + "s", time.getHour()).replace(' ', '0')
                + String.format("%1$" + 2 + "s", time.getMinute()).replace(' ', '0');
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
                + " " + time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }
}
