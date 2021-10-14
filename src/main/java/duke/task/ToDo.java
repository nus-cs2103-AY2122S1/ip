package duke.task;

/**
 * Tasks without any date/time attached
 */
public class ToDo extends Task {
    /**
     * Constructs ToDo object
     *
     * @param name name of task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructs ToDo object
     *
     * @param name name of task
     * @param isDone status of task
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Stores task information in a specific format
     *
     * @return task information
     */
    @Override
    public String toStringInStorage() {
        int done = this.isDone ? 1 : 0;
        return String.format("T/%d/%s", done, this.name);
    }

    /**
     * Returns String of task information
     *
     * @return task information
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
