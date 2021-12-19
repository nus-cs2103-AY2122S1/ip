package duke.logic.tasks;

/**
 * An individual task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;
    
    /**
     * Constructor for the duke.logic.tasks.Task class
     *
     * @param description The description of the task.
     */
    public Task(String description, String tag) {
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    /**
     * Get the description of the task.
     * 
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Return the completion status of the task
     *
     * @return duke.logic.tasks.Task completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark a task as done
     *
     * @return Response whether the task is already marked as done.
     */
    public boolean markAsDone() {
        if (this.isDone) {
            return false;
        } else {
            this.isDone = true;
            return true;
        }
    }

    /**
     * Tags a task with the given tag
     * 
     * @param tag The desired tag
     * @return Response whether the task is tagged.
     */
    public boolean tag(String tag) {
        this.tag = tag;
        return true;
    }
    
    /**
     * Return string representation of the task to write to hard disk.
     *
     * @return The string representation.
     */
    public abstract String toSaveInHardDisk();

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.getDescription() + 
                (this.tag.equals("") ? "" : " #" + this.tag));
    }

}
