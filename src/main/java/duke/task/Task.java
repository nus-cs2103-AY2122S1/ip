package duke.task;

/**
 * Base Task class
 */
public class Task { //Base Task Class
    private final String name;
    protected boolean isDone;

    /**
     * Constructor of Task class
     *
     * @param name Name of Task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as done when called and return it
     */
    public String done() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * Checks if the task is already completed
     * @return if the task has been completed
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the task as the txt format and is used by the other subclasses
     *
     * @return txt format of the task
     */
    public String write() {
        if (this.isDone) {
            return "| 1 | " + this.name;
        } else {
            return "| 0 | " + this.name;
        }
    }

    /**
     * Returns the task as String
     *
     * @return list format of the task
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
