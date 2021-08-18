/**
 * Class for Task, to be added to the TaskList
 * @author Liew Jian Hong
 */

public class Task {

    /**
     * The description of the task.
     */
    private String desc;

    /**
     * The completion status of the task.
     */
    private boolean isDone;

    /**
     * Constructor for a Task
     * @param desc Description of the task details.
     * @param isDone Completion status of the task.
     */
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Mark the task as done, changing its completion status to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Return a string representation of the task.
     * @return Return the completion status and description of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",isDone ? "X" : " " ,this.desc);
    }
}
