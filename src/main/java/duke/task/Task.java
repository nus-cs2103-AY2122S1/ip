package duke.task;



public class Task {
    private String description;
    private boolean isDone;

    /**
     * Public constructor for the Task class
     * @param description description of the class
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return this.description;
    }
    public boolean getIsDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }
    
    public void updateDescription(String updatedDescription) {
        this.description = updatedDescription;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
