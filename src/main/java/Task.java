/**
 * Parent class. Inherited by a bunch of classes like Event and Deadline.
 *
 * @author Ruth Poh (Lab 10H)
 */
public class Task {
    protected String taskstr;
    protected boolean isDone;

    /**
     * Constructor to initialize Deadline.
     *
     * @param taskstr Task.
     */
    public Task(String taskstr) {
        this.taskstr = taskstr;
        this.isDone = false;
    }

    /**
     * Returns string of task.
     *
     * @return string of task.
     */
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + taskstr;
    }

    /**
     * Returns string 'X' if task is done, ' ' if task is not.
     * @return string 'X' or 'x'.
     */
    public String getTaskStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done.
     * @return True if task is marked as done, False if task is already done.
     */
    public boolean markAsDone() {
        if (isDone) {
            return false;
        } else {
            isDone = true;
            return true;
        }
    }
}
