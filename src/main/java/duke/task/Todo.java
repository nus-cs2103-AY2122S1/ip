package duke.task;

/**
 * Represents a specific type of Task like Todos.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task.
     *
     * @param description The description of the task.
     */
    public Todo(String description, String priority) {
        super(description, priority);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Converts an Todo into the format for storing.
     *
     * @return A String specific to a Todo that follows the storing conventions for our data file.
     */
    @Override
    public String convertToFileFormat() {
        return String.format("T%s", super.convertToFileFormat());
    }

}
