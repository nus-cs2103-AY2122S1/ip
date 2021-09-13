package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task.
 * A Deadline consists of a date and time representing the deadline of the task.
 */
public class Deadline extends Task {

    private LocalDate date;
    private LocalTime time;
    private String frequency;
    private boolean isUpdated;

    public Deadline(String toDo, LocalDate date, LocalTime time, String frequency) {
        this(toDo, date, time, frequency, false);
    }

    /**
     * Creates a Deadline object
     *
     * @param toDo name of the task to be done before the deadline
     * @param date deadline date of the task
     * @param time deadline time of the task
     */
    public Deadline(String toDo, LocalDate date, LocalTime time, String frequency, boolean isUpdated) {
        super(toDo);
        this.date = date;
        this.time = time;
        this.frequency = frequency;
        this.isUpdated = isUpdated;
    }

    String getType() {
        return "D";
    }

    String getDateString() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ", "
                + time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    /**
     * Returns the Deadline String that will be written into storage.
     * Date will have the format MMM dd yyyy
     * Time will have the format hh:mm AM/PM
     *
     * @return string with the format "[D] | status | task | date and time of deadline"
     */
    public String getToWrite() {
        return this.getType() + " | " + super.getToWrite() + " | " + this.getDateString() + " | " + frequency;
    }

    /**
     * checks if Event Date was recently updated. Only updates isDone if Event Date was not recently updated, then
     * resets isUpdated
     */
    @Override
    public void complete() {
        if (!this.isUpdated) {
            super.complete();
        }
        this.isUpdated = false;
    }

    /**
     * Returns the Deadline String
     * Date will have the format MMM dd yyyy
     * Time will have the format hh:mm AM/PM
     *
     * @return string with the format "[D] status and task (by: deadline) frequency
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + time.format(DateTimeFormatter.ofPattern("hh:mm a"))
                + ") "
                + frequency;
    }
}