/**
 * The is the Task class that contains the properties
 * of a task.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class Task {
    private final String name;
    private final boolean isDone;

    /**
     * This is constructor method of Task.
     *
     * @param name task name
     */
    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * This is constructor method of Task.
     *
     * @param name   task name
     * @param isDone task status: done or not done
     */
    Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Get status of task.
     *
     * @return task status
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Mark task status as done.
     *
     * @return task with done status
     */
    public Task markAsDone() {
        return new Task(name, true);
    }

    /**
     * Get name of task.
     *
     * @return task name
     */
    public String getName() {
        return name;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Print task with format:
     * If task is done, [X] Task1; else, [ ] Task1.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }
}
