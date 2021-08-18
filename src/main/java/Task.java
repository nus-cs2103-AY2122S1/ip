/**
 * Represents a Task object that can be added
 * to users' to-do list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Task {
    private boolean isDone;
    private String task;

    private Task(String task) {
        this.isDone = false;
        this.task = task;
    }

    /**
     * Creates a new Task object
     *
     * @param task String description of the task
     */
    public static Task createTask(String task) {
        return new Task(task);
    }

    /**
     * Sets the task to be done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * String representation of the Task object
     */
    @Override
    public String toString() {
        String checker = this.isDone ? "X" : " ";
        return "[" + checker + "]" + " " + this.task;
    }
}
