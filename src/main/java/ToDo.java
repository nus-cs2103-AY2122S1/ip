public class ToDo extends TaskItem {

    /**
     * Constructor for creating a ToDo object.
     * @param description the description of the ToDo/TaskItem.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overriden toString() method.
     * @return A String representation of a ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
