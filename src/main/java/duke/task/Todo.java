package duke.task;

public class Todo extends Task {

    /**
     * Initialise constructor for a todo.
     * @param taskName Description of the todo.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns a {@code string} representation of a todo.
     * @return todo name and whether it is completed.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
