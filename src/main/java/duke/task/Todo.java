package duke.task;

/**
 * Encapsulates a to-do task.
 */
public class Todo extends Task {
    private String taskName;
    private String taskSymbol = "T";

    /**
     * Constructs a Todo object.
     * @param taskName
     */
    public Todo(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return String.format("[%s][%s] %s", taskSymbol, statusIcon, taskName);
    }

    @Override
    public String toStorageFormat() {
        return String.format("%s%s%s%s%s", taskSymbol, REGEX_FOR_STORAGE, isCompleted(), REGEX_FOR_STORAGE, taskName);
    }
}
