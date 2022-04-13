package petal.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import petal.components.Parser;

/**
 * Deadline class, subclass of Task
 * Encapsulates Task with end time
 */
public class Deadline extends Task implements Timeable {

    private final String description;
    //dateTime represents the original string passed in of the date and time, used for saving
    private final String dateTime;
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructs a Deadline instance
     *
     * @param description The description of the object
     * @param dateTime The date given by the user
     * @param isDone The boolean isDone, representing if the Task is done
     */
    public Deadline(String description, String dateTime, boolean isDone) {
        super(description.trim(), isDone);

        this.description = description.trim();
        this.dateTime = dateTime.trim();

        String[] splitByWhiteSpace = this.dateTime.split(" ");
        date = Parser.parseDate(splitByWhiteSpace[0]);
        time = Parser.parseTime(splitByWhiteSpace[1]);
    }

    @Override
    public String formatStrForSaving() {
        String formatted = "D|" + this.getStatusIcon() + "|" + this.description + "|" + dateTime;
        return formatted;
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
        String timeOfDeadline = " " + this.time + ")";
        String dateOfDeadline = " (by: " + formatDate;
        return "[D]" + super.toString() + dateOfDeadline + timeOfDeadline;
    }
}
