public class DeadlineTask extends Task {
    protected String by;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.by = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + by + ")";
    }
}