/** Represents a task that can be completed
 * @author mokdarren
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     *
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter for isDone status
     * @return "X" or " "
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.description;
    }
}
