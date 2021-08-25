package duke.task;

/**
 * Represents a "ToDo" task type. It stores details of a ToDo task, such as its name and type.
 */
public class ToDo extends Task {
    private String taskName, type = "T";

    public ToDo(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Returns the name of the ToDo task
     *
     * @return name of the ToDo task
     */
    public String showTask() {
        return taskName;
    }

    /**
     * Returns the type of the ToDo task
     *
     * @return task type "T"
     */
    public String showType() {
        return type;
    }

    public String showWhen() { return ""; }

    public String showTaskOnly() { return ""; }

}
