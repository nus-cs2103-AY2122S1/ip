package duke;

/**
 * Class for duke.Task, to be added to the duke.TaskList
 * @author Liew Jian Hong
 */

public abstract class Task {

    /**
     * The description of the task.
     */
    protected String desc;

    /**
     * The completion status of the task.
     */
    protected boolean isDone;

    /**
     * Constructor for a duke.Task
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

    public abstract String toWrite();
    /**
     * Return a string representation of the task.
     * @return Return the completion status and description of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",isDone ? "X" : " " ,this.desc);
    }


}
