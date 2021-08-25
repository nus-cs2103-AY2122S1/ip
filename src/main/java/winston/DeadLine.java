package winston;

import java.time.LocalDate;


public class DeadLine extends Task {
    private final String type;
    private final LocalDate dueDate;
    
    /**
     * Constructor for DeadLine
     *
     * @param description is the string of the description of the given task
     */
    public DeadLine(String description, String dueDate, boolean isCompleted) {
        super(description, isCompleted);
        this.dueDate = DateHandler.formatDate(dueDate);
        this.type = "D";
    }

    public DeadLine(String description, String dueDate) {
        super(description, false);
        this.type = "D";
        this.dueDate = DateHandler.formatDate(dueDate);
    }

    @Override
    public String saveFormat() {
        return this.type + "," + super.saveFormat() + "," + this.dueDate;
    }

    @Override
    public String toString() {
        return "[" + this.type + "] " + super.toString() + " (Due: " + DateHandler.convertDate(this.dueDate) + ")";
    }
}
