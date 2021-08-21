package duke.task;

import java.time.LocalDate;

/**
 * A task with specified deadline.
 */
public class DeadlineTask extends Task {

    public DeadlineTask(String task) {
        super(task);
        String[] arr = task.split(" /by ", 2);
        this.date = arr.length == 2 ? LocalDate.parse(arr[1]) : null;
        this.name = arr[0];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDateString() + ")";
    }

}
