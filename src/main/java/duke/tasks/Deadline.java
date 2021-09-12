package main.java.duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A deadline task with date specified.
 */
public class Deadline extends Task {
    protected String prefix;
    protected String date;
    protected LocalDate dateFormatted;

    /**
     * Constructs a new deadline task.
     *
     * @param name name of the task
     * @param date date of the task
     */
    public Deadline(String name, String date) {
        super(name);
        this.prefix = "[D]";
        if (canBeFormattedDate(date)) {
            this.date = date;
            this.dateFormatted = fromStringToDate(date.substring(1));
        } else {
            this.date = date;
        }
    }

    private boolean canBeFormattedDate(String date) {
        try {
            LocalDate.parse(date.substring(1), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public LocalDate getDateFormatted() {
        return this.dateFormatted;
    }

    /**
     * Converts a date string to local date format
     *
     * @param dateString a string of date
     * @return a local date of the string
     */
    public LocalDate fromStringToDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    @Override
    public String toString() {
        if (canBeFormattedDate(this.date)) {
            return (this.prefix + " " + super.showStatus() + this.name + ":" + this.dateFormatted + "\n");
        } else {
            return (this.prefix + super.showStatus() + this.name + ":" + this.date + "\n");
        }

    }

    /**
     * Marks a deadline task as done and print out relevant information.
     */
    @Override
    public void markAsDone() {
        this.hasDone = true;
    }
}
