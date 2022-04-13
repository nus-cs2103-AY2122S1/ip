package jared.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts to string for printing list.
     * @return String of todo details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts to string for saving in data.
     * @return String of todo details in save format.
     */
    @Override
    public String saveFormat() {
        return "T _ " + super.saveFormat();
    }
}
