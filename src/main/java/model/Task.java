package model;

import java.io.Serializable;

/**
 * abstract class of task that represents a thing to be done
 */
public abstract class Task implements Serializable {
    private final String desc;
    private boolean isDone = false;
    
    /**
     * Constructor of the abstract class, to be initialized with the subclass.
     *
     * @param desc Description.
     */
    public Task(String desc) {
        this.desc = desc;
    }
    
    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }
    
    /**
     * Gets the status of the task.
     *
     * @return Boolean representing whether the task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }
    
    /**
     * Gets description of the task.
     *
     * @return String representing the description.
     */
    public String getDesc() {
        return this.desc;
    }
    
    /**
     * Returns string representation of task class.
     *
     * @return [X] or [ ] indicating whether the task is done and the description.
     */
    @Override
    public String toString() {
        return (this.isDone() ? "[X] " : "[ ] ") + this.desc;
    }
}
