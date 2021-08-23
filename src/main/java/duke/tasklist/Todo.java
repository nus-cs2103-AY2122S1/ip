package duke.tasklist;

/**
 * Represents a todo task within chat bot.
 * Inherits from task class.
 */
public class Todo extends Task {
    /**
     * Constructs todo object.
     *
     * @param name name of todo.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns custom string of todo task.
     * Includes isDone status and todo task name.
     *
     * @return task description
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns custom string of todo task for saving.
     *
     * @return task save description
     */
    public String save() {
        return String.format("T | %s", super.save());
    }
}
