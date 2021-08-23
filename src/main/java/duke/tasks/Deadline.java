package duke.tasks;

import duke.functionality.Datetime;

public class Deadline extends Task {
    private final Datetime dueDate;
    private static final String TASK_TAG = "deadline";

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = new Datetime(dueDate);
    }

    public Deadline(String taskName, String dueDate, boolean isDone) {
        super(taskName, isDone);
        this.dueDate = new Datetime(dueDate);
    }

    public String fileSaveFormat() {
        return String.format("D | %d | %s | %s", this.isDone() ? 1 : 0, this.taskName(),
                this.dueDate.getDatetimeString());
    }

    public static String taskTag() {
        return TASK_TAG;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate.toString() + ")";
    }
}
