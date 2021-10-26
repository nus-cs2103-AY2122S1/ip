package duke.task;

/**
 * Represents a Todo with a description and a 'done' status.
 */
public class Todo extends Task {

    /**
     * The Constructor for a Todo object.
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo into the specified format for
     * the text file from Storage.
     *
     * @return The file format representation of the Todo.
     */
    @Override
    public String toFileFormat() {
        char done = '0';
        if (super.isDone) {
            done = '1';
        }
        return "T | " + done + " | " + getDescription();
    }

    /**
     * Returns a string representation of the Todo. Appends a
     * "[T]" to indicate it is a Todo.
     *
     * @return A string representation of the Todo.
     */
    @Override
    public String toString() {
        return " [T]" + super.toString();
    }
}
