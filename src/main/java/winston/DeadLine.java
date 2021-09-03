package winston;

import java.time.LocalDate;

/**
 * A class extension of Task representing a deadline task.
 */
public class DeadLine extends Task {
    private final String type;
    private final LocalDate dueDate;
    
    /**
     * Constructor for DeadLine
     *
     * @param description is the string of the description of the given task.
     * @param isCompleted is the status of the task.
     * @param dueDate is the due date of the task in String format.
     */
    public DeadLine(String description, String dueDate, boolean isCompleted) {
        super(description, isCompleted);
        this.dueDate = DateHandler.readDate(dueDate);
        type = "D";
    }

    /**
     * Constructor for DeadLine
     *
     * @param description is the string of the description of the given task.
     * @param dueDate is the due date of the task in String format.
     */
    public DeadLine(String description, String dueDate) {
        super(description, false);
        type = "D";
        this.dueDate = DateHandler.readDate(dueDate);
    }

    /**
     * Method to convert information from object instance into a different format to be saved.
     *   
     * @return a string with the type, completion status, task description and due date.
     */
    @Override
    public String saveFormat() {
        assert(type == "D");
        return this.type + "," + super.saveFormat() + "," + this.dueDate;
    }

    /**
     *  Method to convert information from object instance into a more readable format.
     *   
     * @return a string with the type, completion status, task description and due date.
     */
    @Override
    public String toString() {
        return "[" + type + "] " + super.toString() + " (Due: " + DateHandler.convertDate(dueDate) + ")";
    }
}
