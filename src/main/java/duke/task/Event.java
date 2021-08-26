package duke.task;

import duke.util.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that represents a task that occurs on a certain date, from a start to end time.
 */
public class Event extends Task {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Creates an event task with a task name, date, start time and end time.
     *
     * @param taskName  Short description of the task.
     * @param date      Date for the event.
     * @param startTime Start time for the event.
     * @param endTime   End time for the event.
     * @throws DukeException If start time is later or equal to the end time.
     */
    public Event(String taskName, LocalDate date,
                 LocalTime startTime, LocalTime endTime) throws DukeException {
        super(taskName);
        // If start time is greater than or equal to end time, throw exception
        if (startTime.compareTo(endTime) >= 0) {
            throw new DukeException("Start time of event cannot be later or equal to end time.");
        }
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates an event task with a task name, date, start time, end time,
     * and whether or not the task is done.
     *
     * @param taskName  Short description of the task.
     * @param isDone    Boolean that represents whether or not the task is done.
     * @param date      Date for the event.
     * @param startTime Start time for the event.
     * @param endTime   End time for the event.
     * @throws DukeException If start time is later or equal to the end time.
     */
    public Event(String taskName, boolean isDone, LocalDate date,
                 LocalTime startTime, LocalTime endTime) throws DukeException {
        super(taskName, isDone);
        // If start time is greater than or equal to end time, throw exception
        if (startTime.compareTo(endTime) >= 0) {
            throw new DukeException("Start time of event cannot be later or equal to end time.");
        }
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public Task markAsDone() {
        return new Event(this.taskName, true, this.date, this.startTime, this.endTime);
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
        return !this.date.atTime(this.startTime).isAfter(dateTime);
    }

    @Override
    public String toSaveData() {
        return "E|" + super.toSaveData() + "|" + this.date
                + "|" + this.startTime + "|" + this.endTime;
    }

    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedStartTime = this.startTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        String formattedEndTime = this.endTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return "[E]" + super.toString() + " (at: " + formattedDate + ", "
                + formattedStartTime + " - " + formattedEndTime + ")";
    }
}
