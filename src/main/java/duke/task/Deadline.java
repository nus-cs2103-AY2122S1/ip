package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h2>Deadline</h2>
 * A task which has an additional <code>dateTime</code> field which informs the user when the task needs to be 
 * completed by.
 * @see Event
 * @see ToDo
 */

public class Deadline extends Task {

    private final LocalDateTime dateTime;

    public Deadline(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    private Deadline(Deadline oldDeadline) {
        super(oldDeadline);
        this.dateTime = oldDeadline.dateTime;
    }
    
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

    @Override
    public String toString() {
        return "D: " + super.toString() + " before: " + this.dateTime.format(DateTimeFormatter.ofPattern(
                Task.dateTimeFormat));
    }
}
