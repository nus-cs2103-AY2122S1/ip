package duke.data.task;

import duke.data.exception.EmptyDescriptionException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public abstract String convertToData();
    
    /**
     * Constructor for Task
     * @param description the description of the task
     */
    public Task(String description) throws EmptyDescriptionException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        this.description = description;
        this.isDone = false;
    }
    
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Return icon indicating status of this task
     * @return X if done, empty if otherwise
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Mark this task as done
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
        return String.format("[%s] %s",this.getStatusIcon(), this.description);
    }
}
