package duke;

/**
 * Encapsulates the details of a Todo which is a subtype of Task.
 */
public class Todo extends Task {

    /**
     * Constructor to create a Todo
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the given Todo into an appropriate format for txt file.
     * @return a String of the Todo for input into a txt file.
     */
    @Override
    public String toTxt() {
        return String.format("T | %d | %s",
                super.getIsDone() ? 1 : 0,
                super.getDescription());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
