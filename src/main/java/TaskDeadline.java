public class TaskDeadline extends Task {
    private String by;

    public TaskDeadline(String description, String by) {
        this(description, by, false);
    }

    public TaskDeadline(String description, String by, boolean done) {
        super(description, done);
        this.by = by;
    }

    /**
     * String representation of Deadline
     *
     * @return deadline display
     */
    @Override
    public String toString() {
        String checkBox = done
                ? "[X] "
                : "[ ] ";
        return "[D]" + checkBox + description + " (by: " + by + ")";
    }

    @Override
    String saveString() {
        return "D" + '\t' + (this.done ? "1" : "0") + '\t' + this.description + '\t' + this.by;
    }

}
