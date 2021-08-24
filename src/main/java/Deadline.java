public class Deadline extends Task{
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String getFormattedData() {
        return super.getFormattedData() + "|" + this.deadline;
    }

    @Override
    public String getTaskIdentifier() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + this.description + " (by: " + this.deadline + ")";
    }
}
