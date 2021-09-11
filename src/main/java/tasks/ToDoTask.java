package tasks;

/**
 * The DeadlineTask Class inherits Task Class
 * to support different specificities of a task
 * as per input by user.
 */
public final class ToDoTask extends Task {


    /** Stores the type of task information that identifies a ToDoTask */
    private final String taskType = "[T]";

    /**
     * Constructs a ToDoTask.
     *
     * @param description the input string to describe the task
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Retrieves the information of the type of task.
     *
     * @return the String description of the type of task
     */
    @Override
    public String getType() {
        return this.taskType;
    }

    /**
     * Retrieves the information on the format of the task to be saved.
     *
     * @return the String representation of how the task will be saved
     */
    @Override
    public String getSaveFormat() {
        if (getStatus().equals("[ ]")) {
            return "T" + "|" + getDescription().strip() + "|" + 0;
        } else {
            return "T" + "|" + getDescription().strip() + "|" + 1;
        }
    }
}

