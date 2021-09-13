package duke.data.task;

import duke.data.exception.EmptyDescriptionException;

/**
 * Encapsulates Todo
 */
public class Todo extends Task {
    /** The identifier for this task */
    protected static final String IDENTIFIER = "T";

    /**
     * Constructor for Todo
     *
     * @param description the description of the task
     * @throws EmptyDescriptionException if description is empty
     */
    public Todo(String description) throws EmptyDescriptionException {
        super(description);
    }

    /**
     * Constructor for Todo specifying isDone
     *
     * @param description the description of the task
     * @param isDone the status of the task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String convertToData() {
        return String.format("%s/%s/%s", IDENTIFIER, this.isDone ? "1" : "0", this.description);
    }

    /**
     * Returns string representation of Todo
     *
     * @return string representation of Todo
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", IDENTIFIER, super.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Todo t = (Todo) obj;
            return t.description.equals(this.description);
        }
        return false;
    }
}
