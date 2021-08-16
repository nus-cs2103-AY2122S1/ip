public class TaskDeadline extends Task {
    private String by;

    public TaskDeadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String checkBox = done
                ? "[X] "
                : "[ ] ";
        return "[D]" + checkBox + description + " (by: " + by + ")";
    }

}
