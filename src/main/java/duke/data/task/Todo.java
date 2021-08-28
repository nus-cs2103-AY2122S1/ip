package duke.data.task;

import duke.data.exception.EmptyDescriptionException;

/**
 * Encapsulates Todo 
 */
public class Todo extends Task {
    /**
     * Constructor for Todo
     * @param description the description of the task
     * @throws EmptyDescriptionException if description is empty
     */
    public Todo(String description) throws EmptyDescriptionException {
        super(description);
    }

    /**
     * Constructor for Todo specifying isDone
     * @param description the description of the task
     * @param isDone the status of the task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String convertToData() {
        return String.format("T/%s/%s", this.isDone ? "1" : "0", this.description);
    }

    /**
     * Returns string representation of Todo
     * @return string representation of Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
