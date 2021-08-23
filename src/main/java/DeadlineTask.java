public class DeadlineTask extends Task {
    protected String by;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.by = deadline;
    }

    public DeadlineTask(String isCompleted, String description, String deadline) {
        super(isCompleted, description);
        this.by = deadline;
    }

    public String getDeadline() {
        return this.by;
    }

    public String getType() {
        return "DEADLINE";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + by + ")";
    }
}