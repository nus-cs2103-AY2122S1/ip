package duke.tasks;

/**
 * Class to encapsulate a Task
 */
public abstract class Task implements Comparable<Task> {

    protected String description;
    protected boolean isDone;

    /**
     * Task constructor.
     *
     * @param description String description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns string representation of status, X for done and empty otherwise.
     *
     * @return String icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns string representation of status for saving, 1 for done and 0 otherwise.
     *
     * @return String icon
     */
    public String getSaveIcon() {
        return (isDone ? "1" : "0");
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * To mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * String for saving a task.
     *
     * @return String for saving
     */
    public String toSaveString() {
        return this.getSaveIcon() + " | " + this.description;
    }

    @Override
    public int compareTo(Task o) {
        // 1: T, E, D
        // 2: Not done, Done
        // 3: Lexicographical for T description
        //    DateTime for E, D
        // 4: Lexicographical for E, D description
        if (this instanceof ToDo) {
            if (!(o instanceof ToDo)) {
                return 1;
            }
            if (this.isDone ^ o.isDone) {
                return this.isDone ? 1 : -1;
            }
            return this.description.compareTo(o.description);
        }

        if (this instanceof Event) {
            Event o1 = (Event) this;
            return o1.compareTo(o);
        }

        if (this instanceof Deadline) {
            Deadline o1 = (Deadline) this;
            return o1.compareTo(o);
        }

        return 0;
    }
}
