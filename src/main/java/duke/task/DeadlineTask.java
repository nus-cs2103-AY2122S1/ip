package duke.task;

import java.time.LocalDate;

/**
 * A task with specified deadline.
 */
public class DeadlineTask extends Task {

    /**
     * Creates a task with a deadline.
     *
     * @param name of the task
     * @param date deadline
     */
    public DeadlineTask(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Creates a task with a deadline. Normally used when recreating tasks from the
     * database.
     *
     * @param name      of the task
     * @param completed whether the task is completed
     * @param date      deadline
     */
    public DeadlineTask(String name, boolean completed, LocalDate date) {
        super(name, completed);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDateString() + ")";
    }

}
