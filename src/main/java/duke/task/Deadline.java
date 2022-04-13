package duke.task;

/**
 * Encapsulates a task with a deadline.
 */
public class Deadline extends Task {
    private String taskName;
    private String time;
    private String taskSymbol = "D";

    /**
     * Constructs a deadline object.
     * @param taskName Name of the deadline task.
     * @param time Deadline of the task.
     */
    public Deadline(String taskName, String time) {
        this.taskName = taskName;
        this.time = time;
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return String.format("[%s][%s] %s (by: %s)", taskSymbol, statusIcon, taskName, formatTime(time));
    }

    @Override
    public String toStorageFormat() {
        return String.format("%s%s%s%s%s%s%s", taskSymbol, REGEX_FOR_STORAGE, isCompleted(), REGEX_FOR_STORAGE,
                taskName, REGEX_FOR_STORAGE, time);
    }

}
