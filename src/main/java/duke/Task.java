package duke;

/**
 * Task class representing things to do
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object
     * @param description takes in a String describing the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * gets status of whether a Task is done
     * @return a String representing the status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * gets description of Task
     * @return a String representing Task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * marks a task as done
     */
    public void markAsDone(){
        this.isDone = true;
    }
    
    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description ;
    }
}