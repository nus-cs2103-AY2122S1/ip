package duke.tasks;

import java.time.LocalDateTime;

/**
 * Handles tasks that have a single date.
 */
public class DeadlineTask extends Task {

    private LocalDateTime date;

    /**
     * Creata new Deadline task.
     *
     * @param title description of the task
     * @param date date the task is to be completed by.
     */
    public DeadlineTask(String title, LocalDateTime date) {
        super(title, Type.DEADLINE);
        this.date = date;
    }

    @Override
    public String taskToString() {
        return super.taskToString() + DateParser.toDatabaseFormat(this.date);
    }

    @Override
    public String toString() {
        return "[D] "
                + super.toString()
                + String.format(" (by: %s)", DateParser.toHumanReadable(this.date));
    }
}
