package janet;

/**
 * Represents the action requested by the user that Janet understands.
 */
public class Command {
    private final String operation;
    private int index;
    private String description;
    private String time;

    /**
     * Class constructor specifying an operation that requires no other input.
     *
     * @param operation the operation for Janet to execute
     */
    public Command(String operation) {
        this.operation = operation;
    }

    /**
     * Class constructor specifying an operation that takes a task index.
     *
     * @param operation The operation for Janet to execute
     * @param index The index of the task in Janet's task list
     */
    public Command(String operation, int index) {
        this.operation = operation;
        this.index = index;
    }

    /**
     * Class constructor specifying an operation that takes a description.
     *
     * @param operation The operation for Janet to execute
     * @param description Description of the task
     */
    public Command(String operation, String description) {
        this.operation = operation;
        this.description = description;
    }

    /**
     * Class constructor specifying an operation that takes a description and
     * a time string. The time string will be converted to a LocalDate object
     * if it is of the form YYYY-MM-DD.
     *
     * @param operation The operation for Janet to execute
     * @param description Description of the task
     * @param time Time that the task will occur / should be done by
     */
    public Command(String operation, String description, String time) {
        this.operation = operation;
        this.description = description;
        this.time = time;
    }

    /**
     * Returns the specific operation as a String that Janet is able to understand.
     *
     * @return Janet operation
     */
    public String getOperation() {
        assert(operation != null);
        return operation;
    }

    /**
     * Returns the index of the task in Janet's task list for commands that require
     * the user to specify it.
     *
     * @return Index of task in task list
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the description of the task for commands that require the user to
     * specify it.
     *
     * @return Description of task specified by user
     */
    public String getDescription() {
        assert(description != null);
        return description;
    }

    /**
     * Returns the string representation of the time of tasks for commands that
     * require the user to specify it.
     *
     * @return String representation of task time
     */
    public String getTime() {
        assert(time != null);
        return time;
    }
}


