package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that represents a task that is to be done by a certain date and time.
 */
public class Deadline extends Task {
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Creates a deadline task with a task name, date and time.
     *
     * @param taskName Short description of the task.
     * @param date     Date for the deadline.
     * @param time     Time for the deadline.
     */
    public Deadline(String taskName, LocalDate date, LocalTime time) {
        super(taskName);
        this.date = date;
        this.time = time;
    }

    /**
     * Creates a deadline task with a task name, date, time, and whether or not the task is done.
     *
     * @param taskName Short description of the task.
     * @param isDone   Boolean that represents whether or not the task is done.
     * @param date     Date for the deadline.
     * @param time     Time for the deadline.
     */
    public Deadline(String taskName, boolean isDone,
                    LocalDate date, LocalTime time) {
        super(taskName, isDone);
        this.date = date;
        this.time = time;
    }

    @Override
    public Task markAsDone() {
        return new Deadline(this.taskName, true, this.date, this.time);
    }

    /**
     * Checks if the current task has the same date as the input date.
     *
     * @param date A LocalDate object that contains date information.
     * @return True if the input date and the task's dates are equal, false otherwise.
     */
    @Override
    public boolean hasSameDate(LocalDate date) {
        return this.date.equals(date);
    }

    /**
     * Checks if the current task occurs before the input date and time.
     *
     * @param dateTime A LocalDateTime object that contains date and time information.
     * @return True if the task occurs before the input date, false otherwise.
     */
    @Override
    public boolean isBeforeDate(LocalDateTime dateTime) {
        // Will also return true if the date times are equal
        return !this.date.atTime(this.time).isAfter(dateTime);
    }

    @Override
    public String toSaveData() {
        return "D|" + super.toSaveData() + "|" + this.date + "|" + this.time;
    }

    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTime = this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ", " + formattedTime + ")";
    }
}
