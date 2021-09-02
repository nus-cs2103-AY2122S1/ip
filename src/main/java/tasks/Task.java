package tasks;

import viper.Instruction;

/**
 * Represents a task in Viper.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Instruction type;

    /**
     * Initialises a task with description and its type.
     *
     * @param description Description of given task.
     * @param type Type of task, can be either Todo, event or deadline.
     */
    public Task(String description, Instruction type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Returns the status of task
     * @return X if true, " " if false
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets isDone of Task to true when task is done
     */
    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
