public class DeadLine extends Task {
    private String dueDate;
    private String type;
    
    /**
     * Constructor for DeadLine
     *
     * @param description is the string of the description of the given task
     */
    public DeadLine(String description, String dueDate, boolean isCompleted) {
        super(description, "deadline", isCompleted);
        this.dueDate = dueDate;
        this.type = "D";
    }

    public DeadLine(String description, String dueDate) {
        super(description, "deadline", false);
        this.dueDate = dueDate;
        this.type = "D";
    }

    @Override
    public String saveFormat() {
        return this.type + "," + super.saveFormat() + "," + this.dueDate;
    }

    @Override
    public String toString() {
        return "[" + this.type + "] " + super.toString() + " (Due: " + this.dueDate + ")";
    }
}
