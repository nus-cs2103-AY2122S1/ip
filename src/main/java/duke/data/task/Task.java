package duke.data.task;

import duke.data.exception.EmptyDescriptionException;

/**
 * Encapsulates tasks
 */
public abstract class Task {
    /** Description of task */
    protected String description;
    /** Status of task */
    protected boolean isDone;

    /**
     * Converts content to formatted text to save into storage
     * @return formatted text to save into storage
     */
    public abstract String convertToData();
    
    /**
     * Constructor for Task
     * @param description the description of the task
     * @throws EmptyDescriptionException if description is empty
     */
    public Task(String description) throws EmptyDescriptionException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task specifying isDone
     * @param description the description of the task
     * @param isDone the status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns icon indicating status of this task
     * @return X if done, empty if otherwise
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }
    
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks this task as done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns string representation of this task
     * @return string representation of task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
