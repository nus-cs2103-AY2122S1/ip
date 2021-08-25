package duke.tasks;

/**
 * Class that extends from task and represents an todo task
 */
public class Todo extends Task {
    protected String taskType;

    /**
     * Public constructor of event class
     * @param description
     */
    public Todo(String description) {
        super(description);
        this.taskType = "T";
    }

    /**
     * Gets task type of the todo instance
     *
     * @return "T" meaning todo
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Marks task as completed
     */
    @Override
    public void markAsDone() {
        this.isDone = true;
        System.out.println(this.toString());
    }

    /**
     * Gives string representation of the todo task
     *
     * @return String representation of the todo task
     */
    @Override
    public String toString() {
        return "[" + this.taskType + "]" + super.toString();
    }
}
