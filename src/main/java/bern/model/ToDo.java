package bern.model;

/**
 * A class to represents a Task of type "todo".
 */
public class ToDo extends Task{
    /**
     * Constructor for ToDo.
     *
     * @param description The description of the Task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * A method to return the String representation of the class.
     *
     * @return The String representation of the class.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
