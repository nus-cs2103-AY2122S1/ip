/**
 * This class is to create a task.
 *
 * @author Deng Huaiyu(G12)
 * @version CS2103T AY21/22 Semester 1
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * The method to create a task
     *
     * @param description detail of the task to be created
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * The method to return task status
     *
     * @return string form of the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * The method to print a task
     *
     * @return string form of the task
     */
    @Override
    public String toString() {
        String s = "[" + getStatusIcon() + "] " + this.description;
        return s;
    }

    /**
     * The method to finish a task
     *
     * @return string form of the task after complete it
     */
    public String finished() {
        this.isDone = true;
        return this.toString();
    }
}
