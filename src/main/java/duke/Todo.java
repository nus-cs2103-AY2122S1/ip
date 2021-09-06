package duke;

import java.time.LocalDate;

/**
 * Class for todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task scheduled at the current date.
     *
     * @param description String describing the todo.
     */
    public Todo(String description) {
        super(description, LocalDate.now());
        this.category = TaskType.todo;
        assert description != null : "description should not be null";
    }

    /**
     * Gets string representation of a todo task.
     *
     * @return String describing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
