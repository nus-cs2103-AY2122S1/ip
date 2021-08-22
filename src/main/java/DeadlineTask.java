public class DeadlineTask extends Task {
    private final String deadline;

    public DeadlineTask(String content, boolean isDone, String deadline) {
        super(content, isDone);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
