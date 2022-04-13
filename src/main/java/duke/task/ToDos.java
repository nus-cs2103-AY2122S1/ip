package duke.task;

/**
 * Represents tasks of type todos, inherit from Task abstract class.
 * ToDos has a description.
 */
public class ToDos extends Task {

    /**
     * Constructor of the ToDos class.
     *
     * @param description description of a todos in String.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns the todos in a string for print.
     *
     * @return todos represented in a string for print,
     * in the format: [T] [state] description.
     */
    @Override
    public String toString() {
        return String.format("[T] [%s] " + this.description, this.getStatusIcon());
    }

    /**
     * Returns the todos in a string for sending to the data file to save.
     *
     * @return Todos represented in a string for saving in data file,
     * in the format T|state|description
     */
    @Override
    public String toDataFileString() {
        return String.format("T|%s|%s", this.isDone ? "1" : "0", this.description);
    }
}
