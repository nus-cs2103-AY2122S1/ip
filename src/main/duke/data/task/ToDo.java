package duke.data.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the data representation of a ToDo object.
     *
     * @return The formatted String.
     */
    @Override
    public String toData() {
        return "T|" + super.toData();
    }

    /**
     * Returns the String representation of a ToDo object.
     *
     * @return THe String representation of a ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
