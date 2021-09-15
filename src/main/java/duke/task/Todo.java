package duke.task;

/**
 * Encapsulates the todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for todo object.
     *
     * @param description Details of todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string of todo to be saved.
     *
     * @return String representation of todo to be saved.
     */
    @Override
    public String saveTaskFormat(){
        return "T" + super.saveTaskFormat();
    }

    /**
     * Returns string of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
