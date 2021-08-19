public class DeadlineTask extends Task {

    private String deadline;

    /**
     * Constructor for a DeadlineTask object.
     * @param name name of the task.
     * @param deadline deadline for the task.
     */
    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
