package duke;
public class Todo extends Task {

    /**
     * Constructor for a todo object.
     *
     * @param description the description of the todo object.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
