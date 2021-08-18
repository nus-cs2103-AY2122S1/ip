public class DeadlineTask extends Task {
    private final String deadline;

    public DeadlineTask(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + deadline + ")";
    }
}
