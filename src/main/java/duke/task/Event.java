package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /**
     * Public constructor to initialize an Event object.
     *
     * @param description The description of the event.
     * @param time The time of the event.
     * @param date The date of the event.
     */
    public Event(String description, LocalTime time, LocalDate date) {
        super(description, time, date);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
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
        return "[E]" + super.toString() + " (at: "
                + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + " " + time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }
}
