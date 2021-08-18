/**
 * A type of Task. Inherits from Task, does not have any date/time attached to it.
 */
public class ToDo extends Task{
    /**
     * Constructor for ToDo. Takes in the description of the ToDo.
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String representation of the class
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
