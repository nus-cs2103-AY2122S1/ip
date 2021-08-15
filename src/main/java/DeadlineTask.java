public class DeadlineTask extends Task {
    String deadline;

    public DeadlineTask(String content, boolean isDone, String deadline) {
        super(content, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
