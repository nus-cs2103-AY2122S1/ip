/**
 * a class that encapsulates a task
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * a constructor for Task class
     * initially sets isDone to false, meaning task is not done
     * @param description
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * method that returns the status icon of the task
     * if task is done, returns "X" else returns " "
     * @return status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * method that mark task as done
     * sets isDone to true
     */

    public void markAsDone() {
        isDone = true;
    }

    /**
     * method to print out the task,
     * overrides toString in Object class
     * @return string format of the task, consisting of
     * the status icon and task description
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

}
