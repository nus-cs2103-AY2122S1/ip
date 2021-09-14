package petal.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import petal.components.Parser;

/**
 * Event class, subclass of Task.
 * Encapsulates a Task with a start/end time
 */
public class Event extends Task implements Timeable {

    private final String description;
    //dateTime represents the original string passed in of the date and time, used for saving
    private final String dateTime;
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Constructs an Event instance
     *
     * @param description Description of the event
     * @param dateTime String with the date and start/end timings
     * @param isDone Boolean representing if the event is done or not
     */
    public Event(String description, String dateTime, boolean isDone) {
        super(description.trim(), isDone);

        this.description = description.trim();
        this.dateTime = dateTime.trim();

        String[] splitByWhiteSpace = this.dateTime.split(" ");
        date = Parser.parseDate(splitByWhiteSpace[0]);
        startTime = Parser.parseTime(splitByWhiteSpace[1]);
        endTime = Parser.parseTime(splitByWhiteSpace[2]);
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
        String formatDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(date);
        String startToEndTime = this.startTime + " to " + this.endTime + ")";
        String dateOfEvent = " (at " + formatDate + " ";
        return "[E]" + super.toString() + dateOfEvent + startToEndTime;
    }
}
