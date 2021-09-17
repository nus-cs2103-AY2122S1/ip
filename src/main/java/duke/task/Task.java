package duke.task;

/**
 * A basic type of task from which other types of tasks are built off of.
 */
public class Task {
    /** The name of the Task. */
    private final String NAME;
    /** Whether the Task has been completed. */
    private boolean isCompleted;

    /**
     * Constructs a new Task.
     *
     * @param name The name of the Task.
     */
    public Task(String name) {
        this.NAME = name;
        this.isCompleted = false;
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.isCompleted = true;
    }

    /**
     * Converts the Task to the format used for saving in the storage file on the user's computer.
     *
     * @return The save format of the Task.
     */
    public String convertToSaveFormat() {
        String completedStr = isCompleted ? "1" : "0";
        return completedStr + "|" + NAME;
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * Converts the Task to its string representation.
     *
     * @return The string representation of the Task.
     */
    @Override
    public String toString() {
        String symbol = isCompleted ? "X" : " ";
        return "[" + symbol + "] " + NAME;
    }
}
