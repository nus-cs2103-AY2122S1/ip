package duke.task;

/**
 * Task is an abstract class the specifies the attributes and behaviour of a task.
 *
 * @author leezhixuan
 */
abstract public class Task {
    private boolean isCompleted = false;

    public void setCompleted() {
        this.isCompleted = true;
    }

    /**
     * Returns the status (completed or not) of the Task.
     *
     * @return True for completed, False otherwise.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Returns the Logo of Task.
     *
     * @return Task's logo to indicate the Task type on the ToDoList.
     */
    abstract public String logo();
}
