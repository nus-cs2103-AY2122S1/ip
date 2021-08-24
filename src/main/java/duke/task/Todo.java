package duke.task;

import duke.Parser;

/**
 * This class represents a todo task.
 */

public class Todo extends Task{
    /**
     * Constructs a todo using the given description.
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a todo task using the given description and complete state.
     *
     * @param description the given description.
     * @param isDone the given complete state.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return a string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo following .txt format.
     *
     * @return a string representation of the todo following .txt format.
     */
    @Override
    public String toTxtFormat() {
        return "T" + Parser.SPLITER + super.toTxtFormat();
    }
}
