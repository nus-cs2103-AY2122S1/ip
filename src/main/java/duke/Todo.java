package duke;

/**
 * To-do is a subclass of task to be completed.
 *
 * @author Samuel Lau
 */
public class Todo extends Task {

    /**
     * Constructor for the class.
     *
     * @param description The description of the task to do.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string in the written format
     * to be saved in the text file.
     *
     * @return String to be saved.
     */
    @Override
    public String toWrite() {
        int marked = this.isDone ? 1 : 0;
        return String.format("T|%d|%s\n", marked, this.description);
    }

    /**
     * Returns the string representation of the to-do object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
