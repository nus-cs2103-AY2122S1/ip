package dino.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deadline class, inherits from Task.
 * Deadline are tasks with a description and a "/by " followed by the time and date
 * The time and date must be properly formatted
 */
public class Deadline extends Task {

    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;

    /**
     * Constructor for a Deadline.
     *
     * @param desc String representing the description of the deadline task
     * @param date LocalDate object representing a date
     * @param time LocalTime object representing a time
     */
    public Deadline(String desc, LocalDate date, LocalTime time) {
        this(desc, date, time, false);
    }

    /**
     * Alternate constructor for Deadline, which accepts a boolean indicating the isDone state.
     *
     * @param desc   String representing the description of the deadline task
     * @param date   LocalDate object representing a date
     * @param time   LocalTime object representing a time
     * @param isDone Boolean representing whether the task has been completed
     */
    public Deadline(String desc, LocalDate date, LocalTime time, Boolean isDone) {
        super(desc);
        this.deadlineDate = date;
        this.deadlineTime = time;
        this.dueDate = date.toString() + " " + time.toString();
        this.taskType = "D";
        this.isDone = isDone;
    }

    public LocalDate getDate() {
        return this.deadlineDate;
    }

    public LocalTime getTime() {
        return this.deadlineTime;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " (by: " + deadlineDate.getDayOfMonth() + " "
                + deadlineDate.getMonth() + " " + deadlineDate.getYear() + " "
                + deadlineTime.toString() + ")";
    }
}
