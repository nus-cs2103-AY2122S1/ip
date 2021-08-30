package duke.task;

/**
 * A task with just a description.
 *
 * @author Aiken Wong
 */
public class Todo extends Task {

    protected String taskType = "[T]";

    /**
     * Constructor for Todo.
     *
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for Todo.
     *
     * @param description The description of the Todo.
     * @param isDone Represents whether the task has been completed.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        super.isDone = isDone;
    }

    @Override
    public String toString() {
        return taskType + super.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Todo) {
            Todo todo = (Todo) o;

            return description.equals(todo.description) && isDone == todo.isDone;
        }
        return false;
    }
}
