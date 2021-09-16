package iris.task;

/**
 * Represents a Task
 */
public class Task {
    protected static final String DATE_FORMAT = "MMM d yyyy";
    protected static final String INVALID_DATE_ERROR = "Please enter the date in YYYY-MM-DD format.";
    protected boolean isDone;
    protected TaskPriority taskPriority = TaskPriority.LOW;
    protected String name;

    /**
     * Creates a new Task object
     *
     * @param name name of the Task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Creates a new Task object with given TaskPriority
     * @param name         name of the Task
     * @param taskPriority priority level of the Task
     */
    public Task(String name, TaskPriority taskPriority) {
        this.name = name;
        this.isDone = false;
        this.taskPriority = taskPriority;
    }

    /**
     * Marks Task as complete
     */
    public void markDone() {
        this.isDone = true;
    }

    public boolean contains(String searchTerm) {
        return name.contains(searchTerm);
    }

    private String getStatusIcon() {
        return this.isDone ? "✔️" : "   ";
    }

    String getPriorityIcon() {
        switch (taskPriority) {
        case LOW:
            return "";
        case MEDIUM:
            return "!";
        case HIGH:
            return "!!";
        default:
            assert false;
            return "";
        }
    }

    /**
     * Converts Task object to String
     *
     * @return String representation of Task object
     */
    @Override
    public String toString() {
        return String.format("%s %s %s", this.getPriorityIcon(), this.getStatusIcon(), this.name);
    }

    /**
     * Converts Task object to Command
     *
     * @param index index of this Task in the TaskList
     * @return String representing the command to re-create this Task object
     */
    public String toCommand(int index) {
        return this.isDone ? String.format("done %d\n", index) : "";
    };
}
