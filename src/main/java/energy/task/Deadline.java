package energy.task;

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
        return !date.atTime(time).isAfter(dateTime);
    }

    /**
     * Converts the deadline task data into its corresponding save file data format.
     *
     * @return A string to represent the data of the deadline task in the save file.
     */
    @Override
    public String toSaveData() {
        return "D|" + super.toSaveData() + "|" + date + "|" + time;
    }

    /**
     * Marks the current task as done. Returns a new instance of the task to maintain immutability.
     *
     * @return A task with the same task name, date and time but is marked as done.
     */
    @Override
    public Task markAsDone() {
        return new Deadline(taskName, true, date, time);
    }

    /**
     * Returns a string representation of the current deadline task.
     *
     * @return A string that contains the information of the deadline task.
     */
    @Override
    public String toString() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTime = time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ", " + formattedTime + ")";
    }
}
