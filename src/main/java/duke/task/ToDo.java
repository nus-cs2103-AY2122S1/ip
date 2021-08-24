package duke.task;

/**
 * A task without any date or time attached
 */
public class ToDo extends Task {
    /**
     * Initializes a new Duke.ToDo
     * @param name Name of task
     */
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        String isDone = isDone() ? "x" : " ";
        return String.format("[T][%s] %s", isDone, getName());
    }
}
