package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * A task with specified deadline.
 */
public class DeadlineTask extends Task {

    public DeadlineTask(String task) {
        super(task);
        String[] arr = task.split(" /by ", 2);
        if (arr.length >= 2) {
            try {
                this.date = LocalDate.parse(arr[1]);
            } catch (DateTimeException e) {
            }
        }
        this.name = arr[0];
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
