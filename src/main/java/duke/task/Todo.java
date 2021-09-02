package duke.task;

/**
 * A task of type todo
 */
public class Todo extends Task {

    /**
     * Instantiates a new todo object
     *
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Generates the string representation of todo
     *
     * @return string representation of todo
     */
    @Override
    public String toString() {
        return "T " + super.toString();
    }
}
