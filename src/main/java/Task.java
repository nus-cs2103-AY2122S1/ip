public class Task {
    protected boolean isDone;
    protected String description;

    /**
     * Basic constructor for task (used during subclass instance)
     */
    public Task(){
        setNotDone();
    }

    /**
     * Basic constructor for task, takes in a string that describes the task
     * @param description
     */
    public Task(String description){
        setNotDone();
        setDescription(description);
    }

    /**
     * Basic constructor for an empty task
     * @return Task that has an empty description
     */
    public static Task empty(){
        return new Task("");
    }

    /**
     * Sets the description of the task
     * @param in
     */
    protected void setDescription(String in){
        this.description = in;
    }

    /**
     * Sets the Task state to true
     * @return Task instance itself
     */
    public Task setDone(){
        this.isDone = true;
        return this;
    }

    /**
     * Sets the Task state to false
     * @return Task instance itself
     */
    public Task setNotDone(){
        this.isDone = false;
        return this;
    }

    /**
     * Returns task description
     * @return String
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Returns a string that describes the instance
     * @return String
     */
    @Override
    public String toString(){
        String state = isDone ? "[X] " : "[ ] ";
        return state + this.description;
    }
}

