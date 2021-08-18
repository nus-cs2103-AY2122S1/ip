public class DeadLine extends Task {
    private String dueDate;

    /**
     * Constructor for DeadLine
     *
     * @param description is the string of the description of the given task
     */
    public DeadLine(String description, String dueDate) {
        super(description, "deadline");
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (Due: " + this.dueDate + ")";
    }
}
