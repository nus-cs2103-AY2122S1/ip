package duke.task;

/**
 * Enumeration of the different task types.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-9
 */
public enum TaskType {
    DEADLINE("D"),
    EVENT("E"),
    TODO("T");

    private final String taskType;

    /**
     * Constructor for task type.
     *
     * @param taskType Type of task.
     */
    private TaskType(final String taskType) {
        this.taskType = taskType;
    }

    /**
     * Returns the character representation of task type.
     * @return Character representation of task type.
     */
    public String getTaskType() {
        return taskType;
    }
}
