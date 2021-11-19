package duke.task;

/**
 * Class that extends Task. This task is an event and has no additional information.
 */
public class ToDo extends Task {
    private String tag = "T";

    /**
     * Constructor method for ToDo.
     *
     * @param taskName The main details of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Getter method for obtaining the additional information of the task.
     *
     * @return An empty string as there is no additional information.
     */
    @Override
    public String getAdditionalInfo() {
        return "";
    }

    /**
     * Getter method to obtain the tag of the task.
     *
     * @return "T" to denote ToDo.
     */
    @Override
    public String getTag() {
        return tag;
    }

    /**
     * To String method for tasks.
     *
     * @return The task in String format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
