package duke.task;

/**
 * A task with specified deadline.
 */
public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String task) {
        super(task);
        String[] arr = task.split(" /by ", 2);
        this.deadline = arr.length == 2 ? arr[1] : "";
        this.name = arr[0];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

}
