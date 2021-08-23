package duke.task;

import java.time.LocalDate;

/**
 * A task with specified deadline.
 */
public class DeadlineTask extends Task {

    public DeadlineTask(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public DeadlineTask(String name, boolean completed) {
        super(name, completed);
        this.date = null;
    }

    public DeadlineTask(String name, boolean completed, LocalDate date) {
        super(name, completed);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDateString() + ")";
    }

}
