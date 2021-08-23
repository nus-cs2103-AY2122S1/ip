package duke.task;

/**
 * Represents a ToDo
 */
public class ToDo extends Task {
    /**
     * Create a new ToDo object
     * @param name name of the todo
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Convert ToDo object to String
     * @return String representation of ToDo object
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Convert ToDo object to Command
     * @param index index of this Task in the TaskList
     * @return String representing the command to re-create this ToDo object
     */
    @Override
    public String toCommand(int index) {
        return String.format("todo %s\n%s", this.name, super.toCommand(index));
    }
}
