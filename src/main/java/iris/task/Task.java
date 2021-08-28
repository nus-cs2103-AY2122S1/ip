package iris.task;

/**
 * Represents a Task
 */
public class Task {
    protected boolean isDone;
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
     * Marks Task as complete
     */
    public void markDone() {
        this.isDone = true;
    }

    public boolean contains(String searchTerm) {
        return name.contains(searchTerm);
    }

    private String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Converts Task object to String
     *
     * @return String representation of Task object
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
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
