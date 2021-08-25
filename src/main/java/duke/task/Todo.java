package duke.task;

/**
 * A Todo type task representation for Duke.
 */
public class Todo extends Task {

    /**
     * Create a Todo task.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Todo) {
            Todo otherTodo = (Todo) other;
            return this.description.equals(otherTodo.description) && (this.isDone == otherTodo.isDone);
        }
        return false;
    }
}