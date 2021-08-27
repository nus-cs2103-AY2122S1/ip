package duke.task;

import duke.DukeException;

public class Todo extends Task {

    /** Code representing a todo task */
    public final String code = "T";

    /**
     * Returns a new todo task.
     *
     * @param description Description of the todo task.
     * @throws DukeException If there is missing information or the declaration of the task is of the wrong format.
     */
    public Todo(String description) throws DukeException {
        super(description);
    }

    /**
     * Returns a formatted String representation of the todo task.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[" + code + "]" + super.toString();
    }

    /**
     * Returns the code of the todo task.
     *
     * @return The code representing a todo task.
     */
    @Override
    public String getCode() {
        return this.code;
    }
}
