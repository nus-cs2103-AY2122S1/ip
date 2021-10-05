package duke.task;

/**
 * A task without any date or time attached
 */
public class ToDo extends Task {
    /**
     * Initializes a new ToDo task
     *
     * @param name Name of task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Initializes a new ToDo task with the done property
     *
     * @param name Name of task
     * @param isDone Whether task is done or not
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        String isDone = isDone() ? "✔" : " ";
        return String.format("[T][%s] %s", isDone, getName());
    }
}
