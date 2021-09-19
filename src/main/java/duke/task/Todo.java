package duke.task;

public class Todo extends Task {
    private String taskType = "[T]";

    /**
     * Initialises a Todo object.
     *
     * @param taskName the task name
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Initialises a Todo object with its status.
     *
     * @param taskName the task name
     * @param isDone the status of the todo task
     */
    public Todo(String taskName, boolean isDone) {
        super(taskName);
        if (isDone) {
            this.markAsDone();
        }
    }

    @Override
    public String toString() {
        String result = taskType + this.getCheckBox()
                + " " + this.getTaskName();
        return result;
    }
}
