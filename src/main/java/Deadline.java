// class that handles Deadline tasks 
// -> Deadline is a type of Task with a do by date/time
public class Deadline extends Task {
    private String dueDate;

    // Constructor for a Deadline
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    // Constructor for a Deadline that may be completed
    public Deadline(String description, String dueDate, Boolean isComplete) {
        super(description, isComplete);
        this.dueDate = dueDate;
    }

    @Override
    public String getFileRepr() {
        return "D" + super.getFileRepr() + " | " + this.dueDate;
    }

    // String representation of a Deadline
    @Override
    public String toString() {
        return "[D]["
            + ((this.isCompleted()) ? "X] " : " ] ")
            + this.getDescription()
            + " (by: " 
            + this.dueDate 
            + ")";
    }
}
