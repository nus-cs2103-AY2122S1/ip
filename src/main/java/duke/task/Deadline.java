package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deadline class, inherits from Task
 * Deadline are tasks with a description and a "/by " followed by the time and date
 * The time and date must be properly formatted
 */
public class Deadline extends Task {

    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;

    /**
     * Constructor for a Deadline
     *
     * @param description String representing the description of the deadline task
     * @param date        LocalDate object representing a date
     * @param time        LocalTime object representing a time
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.deadlineDate = date;
        this.deadlineTime = time;
        this.dueDate = date.toString() + " " + time.toString();
        this.taskType = "D";
    }

    public Deadline(String description, LocalDate date, LocalTime time, Boolean isDone) {
        super(description);
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
        return "[" + taskType + "]" + super.toString() + " (by: " + deadlineDate.getDayOfMonth() + " " +
                deadlineDate.getMonth() + " " + deadlineDate.getYear() + " " +
                deadlineTime.toString() + ")";
    }
}