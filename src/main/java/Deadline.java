/**
 * This class represents a deadline, a task with a deadline.
 */
public class Deadline extends Task {
    String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Gives the type of task.
     *
     * @return D for Deadline
     */
    @Override
    public String type() {
        return "D";
    }

    /**
     * Gives save-friendly information.
     *
     * @return save-friendly information.
     */
    @Override
    public String getSaveInfo() {
        return super.getSaveInfo() + "|" + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.deadline + ")";
    }
}
