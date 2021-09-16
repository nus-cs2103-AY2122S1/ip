package lifeline.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class encapsulates a task with a deadline.
 */
public class Deadline extends Task {

    private LocalDateTime by;
    private LocalDate dueDate;
    private LocalTime dueTime;

    /**
     * Default constructor for a Deadline.
     *
     * @param name Name of the task with a deadline.
     * @param dueDate Indicates the date that the task has to completed by.
     * @param dueTime Indicates the time that the task has to completed by.
     */
    public Deadline(String name, LocalDate dueDate, LocalTime dueTime) {
        super(name);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    /**
     * Constructor to be used when loading saved deadline.
     *
     * @param name Name of the task with a deadline.
     * @param dueDate Indicates the date that the task has to completed by.
     * @param dueTime Indicates the time that the task has to completed by.
     * @param isDone Indicates if the task is completed.
     */
    public Deadline(String name, LocalDate dueDate, LocalTime dueTime, boolean isDone) {
        super(name, isDone);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        String dateAsString = dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String timeAsString = dueTime.format(DateTimeFormatter.ofPattern("h:mm a"));
        return "[D]" + super.toString() + " (by: " + dateAsString + " " + timeAsString + ")";
    }

}
