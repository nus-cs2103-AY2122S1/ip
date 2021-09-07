package duke.task;

public class Deadline extends Task {
    private String taskType = "[D]";

    public Deadline(String taskName, String deadline) {
        super(taskName, deadline);
    }

    public Deadline(String taskName, String deadline, boolean isDone) {
        super(taskName, deadline);
        if (isDone) {
            this.markAsDone();
        }
    }

    public String getDeadline() {
        return String.format("(by: %s)", this.getDateAndTime());
    }

    @Override
    public String toString() {
        String result = taskType + this.getCheckBox()
                + " " + this.getTaskName() + " "
                + getDeadline();
        return result;
    }
}
