package duke.task;


/**
 * Represents a task without any date or time attached to it.
 * E.g. walk the dog.
 */
public class Todo extends Task {

    /**
     * Class constructor.
     * @param description Description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns formatted string to write to the duke.txt file.
     *
     * @return String to write to duke.txt
     */
    @Override
    public String toWrite() {
        String done = this.isDone ? "1" : "0";
        return String.format("T | %s | %s", done, this.getDescription());
    }


    /**
     * Returns the string representation of the Todo.
     *
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
