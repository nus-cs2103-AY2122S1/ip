/**
 * Encapsulates a task to be completed
 */
public class Task {
    private String name = "";
    private boolean completed = false;

    /**
     * Task constructor
     *
     * @param name Name of task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks task as complete
     */
    public void complete() {
        this.completed = true;
    }

    /**
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + (completed ? "X" : "") + "] " + this.name;
    }
}
