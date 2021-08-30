package seedu.duke.tasks;

public class Task {
    protected final String description;
    protected final String dateTime;
    protected final boolean isDone;

    /**
     * Constructor. Default having the isDone parameter to be set as false and
     * dateTime set as none.
     * 
     * @param description is the description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.dateTime = "";
        this.isDone = false;
    }

    /**
     * Second Constructor. Default having the isDone parameter to be set as false.
     * 
     * @param description is the description of the Task.
     * @param dateTime    is the description of the location and time for the Task.
     */
    public Task(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
        this.isDone = false;
    }

    /**
     * Third Constructor. Default having the dateTime parameter to be set as empty
     * string as it is not needed.
     * 
     * @param description is the description of the Task.
     * @param isDone      determine whether the task is completed or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.dateTime = "";
        this.isDone = isDone;
    }

    /**
     * Fourth Constructor. Initialising the parameters of the Task.
     * 
     * @param description is the description of the Task.
     * @param dateTime    is the description of the location and time for the Task.
     * @param isDone      determine whether the task is completed or not.
     */
    public Task(String description, String dateTime, boolean isDone) {
        this.description = description;
        this.dateTime = dateTime;
        this.isDone = isDone;
    }

    /**
     * Retrieves the dateTime of the current Task.
     * 
     * @return a String, the dateTime of the current Task.
     */
    public String getDateTime() {
        return this.dateTime;
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
