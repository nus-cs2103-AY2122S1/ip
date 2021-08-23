package duke.tasks;

public class Task {
    protected final String description;
    protected final boolean isDone;

    /**
     * Contructor. Default having the isDone parameter to be set as false.
     * 
     * @param description is the description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Second Constructor which takes in additional boolean, to be able to set the
     * initial boolean status.
     * 
     * @param description is the description of the Task.
     * @param isDone      determine whether the task is completed or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Retrieves the isDone property of the current object.
     * 
     * @return the current isDone property of this object.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Retrieves the symbol of the current object. Different object and child have
     * different symbols that represents them.
     * 
     * @return a fixed parent symbol "parent-task".
     */
    public String getSymbol() {
        return "parent-task";
    }

    /**
     * Retrieves the description of the current object.
     * 
     * @return a String which describes the current object.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the status icon which depends on the isDone status of the current
     * object.
     * 
     * @return "X" if isDone is true, else it will return " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the current Task as done.
     * 
     * @return a new Task object with the same description, but setting isDone
     *         property to be true
     */
    public Task markAsDone() {
        return new Task(this.getDescription(), true);
    }

    /**
     * Describes the current Task object.
     * 
     * @return a description of the current ToDos object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
