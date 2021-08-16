/**
 * The to.do class that is a subclass of Task
 * (Used a . in between to prevent IntelliJ from highlighting it)
 */
public class ToDo extends Task {

    /**
     * Constructor for the to.do class
     * @param description The description of the task
     */
    public ToDo(String description) {
        super(description.trim());
    }

    /**
     * Overridden toString method for the to.do class
     * @return String representation of to.do object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

