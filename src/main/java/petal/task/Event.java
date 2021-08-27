package petal.task;

import petal.components.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Event class, subclass of Task.
 * Encapsulates a Task with a start/end time
 */
public class Event extends Task implements Timeable {

    private final String description;
    private final String dateTime;
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Constructor for the Event class
     *
     * @param description Description of the event
     * @param dateTime String with the date and start/end timings
     * @param isDone Boolean representing if the event is done or not
     */
    public Event(String description, String dateTime, boolean isDone) {
        super(description = description.trim(), isDone);
        this.description = description;
        this.dateTime = (dateTime = dateTime.trim());
        String[] splitByWhiteSpace = dateTime.split(" ");
        this.date = Parser.parseDate(splitByWhiteSpace[0]);
        this.startTime = Parser.parseTime(splitByWhiteSpace[1]);
        this.endTime = Parser.parseTime(splitByWhiteSpace[2]);
    }

    @Override
    public String formatStrForSaving() {
        return "E|" + this.getStatusIcon() + "|" + this.description + "|" + this.dateTime;
    }

    @Override
    public boolean isTimeable() {
        return true;
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at " + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .format(date) + " " + this.startTime + " to " + this.endTime + ")";
    }
}
