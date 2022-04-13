package duke;

public class Task {
    private final String description;
    private boolean isDone;
    private String isDoneBox;
    private String taskType;

    /**
     * Constructor for task class.
     *
     * @param description Description of task.
     * @param taskType String format of task type.
     * @throws TaskDescriptionException
     */
    public Task(String description, String taskType) throws TaskDescriptionException {
        if (description.equals("")) {
            throw new TaskDescriptionException();
        } else {
            this.description = description;
            this.isDone = false;
            this.isDoneBox = "[ ]";
            this.taskType = taskType;
        }
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the checkbox of the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
        this.isDoneBox = "[X]";
    }

    @Override
    public String toString() {
        return this.taskType
                + this.isDoneBox
                + " "
                + this.getDescription();
    }
}
