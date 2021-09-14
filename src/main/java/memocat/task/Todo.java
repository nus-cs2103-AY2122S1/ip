package memocat.task;

/**
 * A Todo type task representation for memocat.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task.
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

    @Override
    public int compareTo(Task task) {
        // always put todo task before other types
        // do not sort it for now
        return -1;
    }
}
