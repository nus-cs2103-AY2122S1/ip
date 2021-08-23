package duke.task;

import duke.exception.DukeException;
import duke.util.Parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class represents tasks that start at a specific time and ends at
 * a specific time e.g., team project meeting on 2/10/2019 2-4pm.
 *
 * @author Chng Zi Hao
 */
public class Event extends Task{
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor for Event.
     *
     * @param description The description of the event.
     */
    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private String formatDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String date = this.date.format(formatter);
        return date + " " + this.startTime.toString() + "-" + this.endTime.toString();
    }

    @Override
    public boolean checkDate(LocalDate date) {
        return this.date.equals(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.formatDateTime() + ")";
    }

    @Override
    public String format() {
        return String.format("E | %s | %s %s-%s", super.format(), this.date, this.startTime, this.endTime);
    }
}
