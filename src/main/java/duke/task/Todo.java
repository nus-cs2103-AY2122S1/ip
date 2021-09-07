package duke.task;

import duke.DukeException;

/**
 * This class represents a {@code Todo} task.
 *
 * @author Eizabeth Chow
 */
public class Todo extends Task {
    /**
     * Constructs a {@code Todo} task with the given title.
     *
     * @param title Title of {@code Todo} task.
     */
    public Todo(String title) {
        super(title);
    }

    /**
     * Constructs a {@code Todo} task with the given title and status.
     *
     * @param title  Title of {@code Todo}.
     * @param isDone Status of {@code Todo}.
     */
    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    public Todo setDate(String deadline) {
        throw new DukeException("Cannot update deadline for TODO task.");
    }
    /**
     * {@inheritDoc} Adds "T |" infront to indicate that it is a {@code Todo} task.
     */
    @Override
    public String toFileString() {
        return String.format("T | %s", super.toFileString());
    }

    /**
     * Returns a String representation of a Todo task. Starts "[T]" to indicate that
     * it is a Todo task.
     *
     * @return String representation of a Todo.
     */
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
