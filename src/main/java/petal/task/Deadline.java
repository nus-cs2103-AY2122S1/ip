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
    private final String dateTime;
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructor for the Deadline class
     *
     * @param description The description of the object
     * @param dateTime The date given by the user
     * @param isDone The boolean isDone, representing if the Task is done
     */
    public Deadline(String description, String dateTime, boolean isDone) {
        super(description = description.trim(), isDone);
        this.description = description;
        this.dateTime = (dateTime = dateTime.trim());
        String[] splitByWhiteSpace = dateTime.split(" ");
        this.date = Parser.parseDate(splitByWhiteSpace[0]);
        this.time = Parser.parseTime(splitByWhiteSpace[1]);
    }

    @Override
    public String formatStrForSaving() {
        return "D|" + this.getStatusIcon() + "|" + this.description + "|" + dateTime;
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
        return "[D]" + super.toString() + " (by: "
                + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(date)
                + " " + this.time + ")";
    }
}
