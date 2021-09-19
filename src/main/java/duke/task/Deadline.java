package duke.task;

public class Deadline extends Task {
    private String taskType = "[D]";

    /**
     * Initialises a Deadline object.
     *
     * @param taskName the task name
     * @param deadline the deadline
     */
    public Deadline(String taskName, String deadline) {
        super(taskName, deadline);
    }

    /**
     * Initialises a Deadline object with its current status.
     *
     * @param taskName the task name
     * @param deadline the deadline
     * @param isDone the status of the deadline task
     */
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
