
public class Task {

    private String name;
    private boolean isDone = false;

    /**
     * Constructor for a Task object.
     * @param name Task name.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Returns a character based on whether or not Task is done.
     * @return 'X' if Task is done, ' ' otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the Task as 'done'.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String representation of the Task object.
     * @return Task name and whether or not Task is done.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
