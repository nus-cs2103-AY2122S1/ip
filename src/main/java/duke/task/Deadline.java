package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h2>Deadline</h2>
 * A task which has an additional <code>dateTime</code> field which informs the user when the task needs to be
 * completed by.
 * @see Event
 * @see ToDo
 */

public class Deadline extends Task implements DateTimeTask {

    private final LocalDateTime dateTime;

    /**
     * Creates a new Deadline task. This is the default constructor since newly added tasks are by default not
     * completed
     * @param taskName the name of the task to create
     * @param dateTime the date and time the task is to be completed by
     */
    public Deadline(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    private Deadline(Deadline oldDeadline) {
        super(oldDeadline);
        this.dateTime = oldDeadline.dateTime;
    }

    /**
     * Factory method that creates deadline objects that can either be completed or not completed
     * @param name the name of the task
     * @param isCompleted whether the deadline task to be created is completed or not
     * @param dateTime the date and time the deadline task needs to be completed by
     * @return a new deadline task object
     */
    public static Deadline createTask(String name, boolean isCompleted, LocalDateTime dateTime) {
        Deadline d = new Deadline(name, dateTime);
        if (isCompleted) {
            return new Deadline(d);
        } else {
            return d;
        }
    }

    /**
     * {@inheritDoc}
     * @return a new <code>Deadline</code> which is exactly the same except with completion status set to
     * <code>true</code>.
     */
    @Override
    public Deadline markAsCompleted() {
        return new Deadline(this);
    }

    public LocalDate getDate() {
        return this.dateTime.toLocalDate();
    }

    @Override
    public String toString() {
        return "D: " + super.toString() + " before: " + this.dateTime.format(DateTimeFormatter.ofPattern(
                Task.DATE_TIME_FORMAT));
    }
}
