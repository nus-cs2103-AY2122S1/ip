package duke;

public class ToDo extends TaskItem {

    private boolean readFromFile = false;

    /**
     * Constructor for creating a duke.ToDo object.
     * @param description the description of the duke.ToDo/duke.TaskItem.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overriden toFileString method. Writes the task item into the dukeData file.
     * @return
     */
    @Override
    public String toFileString() {
        return "[T]" + super.toString();
    }

    /**
     * Overriden toString() method.
     * @return A String representation of a duke.ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
