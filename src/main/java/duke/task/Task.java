package duke.task;

/**
 * Represents a Task
 */
public class Task {
    protected boolean completed = false;
    protected String name;

    /**
     * Create a new Task object
     * @param name name of the Task
     */
    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    /**
     * Mark Task as complete
     */
    public void markComplete() {
        this.completed = true;
    }

    private String getStatusIcon() {
        return this.completed ? "X" : " ";
    }

    /**
     * Convert Task object to String
     * @return String representation of Task object
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    }

    /**
     * Convert Task object to Command
     * @param index index of this Task in the TaskList
     * @return String representing the command to re-create this Task object
     */
    public String toCommand(int index) {
        return this.completed ? String.format("done %d\n", index) : "";
    };
}