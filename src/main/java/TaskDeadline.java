public class TaskDeadline extends Task {
    private String by;

    public TaskDeadline(String description, String by) {
        super(description);
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

}
