/**
 * The ToDo class that is a subclass of Task
 */
public class ToDo extends Task {

    /**
     * Constructor for the ToDo class
     * @param description The description of the task
     */
    public ToDo(String description) {
        super(description.trim());
    }

    /**
     * Overridden toString method for the ToDo class
     * @return String representation of ToDo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

