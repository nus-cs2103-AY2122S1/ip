package duke;

public class ToDo extends Task {

    /**
     * Creates a ToDo object to store Task details.
     *
     * @param description Description of Task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
