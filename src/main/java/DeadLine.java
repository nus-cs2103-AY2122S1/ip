import java.time.LocalDate;


public class DeadLine extends Task {
    private LocalDate dueDate;

    /**
     * Constructor for DeadLine
     *
     * @param description is the string of the description of the given task
     */
    public DeadLine(String description, String dueDate) {
        super(description, "deadline");
        this.dueDate = DateHandler.formatDate(dueDate);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (Due: " + DateHandler.convertDate(this.dueDate) + ")";
    }
}
