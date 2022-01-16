package duke.task;

import java.time.LocalDate;

/**
 * A task specifying an event with a date and time.
 */
public class EventTask extends Task {

    /**
     * Creates a task with an event date.
     *
     * @param name of the task
     * @param date of event
     */
    public EventTask(String name, LocalDate date) {
        super(name, date);
    }

    /**
     * Creates a task with an event date.
     *
     * @param name        of the task
     * @param isCompleted whether the task is completed
     * @param date        of event
     */
    public EventTask(String name, boolean isCompleted, LocalDate date) {
        super(name, isCompleted, date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateString() + ")";
    }

}
