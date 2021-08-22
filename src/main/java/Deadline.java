public class Deadline extends Task {
    private final String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String listEntry() {
        return "[D]" + super.listEntry() + " (by: " + deadline + ")";
    }
    
    @Override
    public String databaseEntry() {
        return "D" + super.databaseEntry() + " | " + deadline;
    }
}
