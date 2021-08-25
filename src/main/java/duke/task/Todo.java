package duke.task;

import duke.Parser;

/**
 * This class represents a todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a todo using the given description.
     *
     * @param description The given description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a todo task using the given description and complete state.
     *
     * @param description The given description.
     * @param isDone      The given complete state.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return A string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo following .txt format.
     *
     * @return A string representation of the todo following .txt format.
     */
    @Override
    public String toTxtFormat() {
        return "T" + Parser.SPLITER + super.toTxtFormat();
    }

    /**
     * Returns true if the given object is equal to this, otherwise false.
     *
     * @param obj The given object.
     * @return True if the given object is equal to this, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Todo) {
            Todo todo = (Todo) obj;
            return (this.getDescription().equals(todo.getDescription())) && (this.isDone() == todo.isDone());
        } else {
            return false;
        }
    }
}
