public class DeadlineTask extends Task {

    private String deadline;

    /**
     * Constructor for a DeadlineTask object.
     * @param name name of the task.
     * @param isDone whether or not task is done.
     * @param deadline deadline for the task.
     */
    public DeadlineTask(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String formatForFile() {
        return "D" + super.formatForFile() + SAVE_DATA_MARKER + this.deadline + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
