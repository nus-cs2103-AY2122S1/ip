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

    /**
     * Returns the Task description.
     *
     * @return Task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representing this object.
     *
     * @return String representing this object
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string used for saving a task.
     *
     * @return String for saving
     */
    public String toSaveString() {
        return this.getSaveIcon() + " | " + this.description;
    }

    /**
     * Returns -1, 0, 1 to implement a comparison rank between Task objects.
     *
     * @param o Object to be compared to
     * @return integer 0 is equal, -1 is less and 1 is more
     */
    @Override
    public int compareTo(Task o) {
        // 1: T, E, D
        // 2: Not done, Done
        // 3: Lexicographical for T description
        //    DateTime for E, D
        // 4: Lexicographical for E, D description
        if (this instanceof ToDo) {
            ToDo o1 = (ToDo) this;
            return o1.compareTo(o);
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
