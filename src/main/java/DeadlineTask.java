public class DeadlineTask extends Task {

    private String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getTaskStatus() {
        return "[D]" + super.getTaskStatus();
    }

    @Override
    public String toString() {
        return super.toString() + " " + "(by: " + this.deadline + ")";
    }
}
