package duke.task;

import duke.exception.InvalidTaskException;

/**
 * A type of task which only contains the name of the task.
 */
public class Todo extends Task {
    /**
     * Constructor of Todo task.
     *
     * @param task The name of task.
     * @throws InvalidTaskException Throws when the taskName is empty.
     */
    public Todo(String task) throws InvalidTaskException {
        super(task);
        if (task.isBlank()) {
            throw new InvalidTaskException("Todo");
        }
    }

    /**
     * Constructor of Todo task, indicating whether the task is done or not.
     *
     * @param task   The name of task.
     * @param isDone Whether the task is done or not.
     * @throws InvalidTaskException Throws when the taskName is empty.
     */
    public Todo(String task, boolean isDone) throws InvalidTaskException {
        super(task, isDone);
        if (task.isBlank()) {
            throw new InvalidTaskException("Todo");
        }
    }

    /**
     * Returns the String representation of the todo task.
     *
     * @return The String representation of the todo task.
     */
    @Override
    public String toString() {
        String finished = " ";
        if (this.isDone()) {
            finished = "X";
        }
        return "[T]" + "[" + finished + "] " + this.getTaskName();
    }

    /**
     * Returns the representation of the todo task when it is stored.
     *
     * @return the representation of the todo task when it is stored.
     */
    @Override
    public String toStoredString() {
        int finished = isDone() ? 1 : 0;
        return "T | " + finished + " | " + this.getTaskName();
    }

    /**
     * Compares this object with a given object.
     *
     * @param comparedObject The object compared with this object.
     * @return Returns true if they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object comparedObject) {
        if (!(comparedObject instanceof Todo)) {
            return false;
        }
        Todo comparedTodoTask = (Todo) comparedObject;
        return comparedTodoTask.toString().equals(this.toString());
    }
}
