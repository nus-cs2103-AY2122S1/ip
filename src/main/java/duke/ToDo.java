package duke;

/**
 * Class representing a ToDo Task.
 */
public class ToDo extends Task {

    /**
     * Constructor of a ToDo, defaults to not completed.
     *
     * @param description Description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor of a ToDo.
     *
     * @param description Description of the ToDo.
     * @param isCompleted If the Todo is completed.
     */
    public ToDo(String description, Boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * Returns String representation of the ToDo.
     *
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns String representation of the ToDo in save format.
     *
     * @return String representation of the ToDo in save format.
     */
    @Override
    public String save() {
        return "T|" + (this.getStatus() ? "1" : "0") + "|" + this.getDescription();
    }
}