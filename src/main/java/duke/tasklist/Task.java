package duke.tasklist;

/**
 * Represents a task within chat bot.
 */
public class Task {
    private final String name;
    private boolean isDone;

    /**
     * Constructs task object.
     *
     * @param name name of task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns custom string of task.
     * Includes isDone status and task name.
     *
     * @return task description
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), name);
    }

    /**
     * Returns completion status/mark/indication.
     * If isDone, X.
     * Else, blank.
     *
     * @return done status.
     */
    public String getStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets task to complete.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns name of task.
     *
     * @return name of task.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns custom string of task for saving.
     *
     * @return task save description.
     */
    public String save() {
        int done = isDone ? 1 : 0;
        return String.format("%d | %s", done, name);
    }
}
