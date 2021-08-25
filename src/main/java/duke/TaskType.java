package duke;

/**
 * Enum representing the type of a task.
 */
public enum TaskType {
    DEADLINE("D"),
    EVENT("E"),
    TODO("T");

    private final String taskIcon;
    private TaskType(String taskIcon) {
        this.taskIcon = taskIcon;
    }

    /**
     * Getter for task icon.
     * @return Task icon corresponding to type of task.
     */
    public String getTaskIcon() {
        return this.taskIcon;
    }

    /**
     * Identify type of task.
     * @param task Task.
     * @return Type of input task.
     */
    public static TaskType identifyTask(Task task) {
        return task instanceof Todo
            ? TaskType.TODO
            : task instanceof Deadline
            ? TaskType.DEADLINE
            : task instanceof Event
            ? TaskType.EVENT
            : null;
    }

    /**
     * Convert task icon into a task type.
     * @param taskIcon Task icon.
     * @return Task type corresponding to input task icon.
     */
    public static TaskType convertTaskIcon(String taskIcon) {
        switch (taskIcon) {
        case "D":
            return TaskType.DEADLINE;
        case "E":
            return TaskType.EVENT;
        case "T":
            return TaskType.TODO;
        default:
            throw new UnsupportedOperationException("unknown task icon");
        }
    }
};
