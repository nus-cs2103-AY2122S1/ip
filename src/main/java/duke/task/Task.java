package duke.task;

/**
 * Parent class of tasks that have a description and can be marked as done.
 *
 * @author Gabriel Goh
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create a task.
     *
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if task description contains string.
     *
     * @param searchKey Search string
     * @return true if contains string
     */
    public boolean containsString(String searchKey) {
        return this.description.toLowerCase().contains(searchKey.toLowerCase());
    }

    /**
     * Returns done status in string format
     *
     * @return X if done else blank
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Converts task to savable format.
     *
     * @return String to save
     */
    public String saveString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns string representation of a task for printing
     *
     * @return String to print
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns true if tasks have the same description and class.
     *
     * @param object Another task
     * @return True if tasks are the same
     */
    @Override
    public boolean equals(Object object) {
        if (object == null || !(object.getClass().equals(this.getClass()))) {
            // getClass to inherit in subclasses of class
            return false;
        } else {
            Task t = (Task) object;
            if (this.description == null) {
                return (t.description == null);
            } else {
                return (this.description.equalsIgnoreCase(t.description));
            }
        }
    }

    /* List of task types */
    public enum TaskTypes {
        TODO,
        DEADLINE,
        EVENT
    }
}
