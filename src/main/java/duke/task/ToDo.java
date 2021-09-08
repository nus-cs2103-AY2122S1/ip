package duke.task;

/**
 * Represents a "ToDo" task type. It stores details of a ToDo task, such as its name and type.
 */
public class ToDo extends Task {
    private String taskDescription;
    private String type = "T";

    public ToDo(String taskDescription) {
        assert taskDescription != null : "task description cannot be null";

        this.taskDescription = taskDescription;
    }

    /**
     * Returns the name of the ToDo task
     *
     * @return name of the ToDo task
     */
    public String showFullDescription() {
        return taskDescription;
    }

    /**
     * Returns the type of the ToDo task
     *
     * @return task type "T"
     */
    public String showType() {
        return type;
    }

    public String showWhen() {
        return "";
    }

    public String showTaskName() {
        return taskDescription;
    }

}
