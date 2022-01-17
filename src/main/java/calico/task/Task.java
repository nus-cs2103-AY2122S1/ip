package calico.task;

import java.util.Objects;

/**
 * Outlines a general task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected char category;
    protected String name;

    /**
     * Initialises properties of a Task.
     *
     * @param description Description about the task.
     * @param category Type of task.
     */
    public Task(String description, char category) {
        this.description = description;
        this.isDone = false;
        this.category = category;
        this.name = "";
    }

    /**
     * Returns X if task is done and a whitespace if task is not yet done.
     *
     * @return Whether task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns information about the task.
     *
     * @return Description of task.
     */
    public String getDesc() {
        return this.description;
    }

    /**
     * Retrieves type of the task.
     *
     * @return Category of task.
     */
    public char getCat() {
        return this.category;
    }

    /**
     * Retrieves end timing of task.
     *
     * @return Due time of task.
     */
    public String getDueTime() {
        return "";
    }

    /**
     * Returns task name.
     *
     * @return Name of task.
     */
    public String getName() {
        return this.name;
    }


    /**
     * Converts Task to string format.
     *
     * @return Task as a string.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Checks if the object is the same Task.
     *
     * @param obj Object to be compared to.
     * @return If object is equal to this Task.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task task = (Task) obj;
            boolean isSameDescription = this.description.equals(task.description);
            boolean isSameName = this.name.equals(task.name);
            boolean isSameCategory = this.category == task.category;
            boolean isSameDone = this.isDone == task.isDone;
            return isSameDescription && isSameName && isSameCategory && isSameDone;
        }
        return false;
    }

    /**
     * Generates hash for task object.
     *
     * @return Integer hash of Task.
     */
    @Override
    public int hashCode() {
        return Objects.hash(description, isDone, category, name);
    }
}
