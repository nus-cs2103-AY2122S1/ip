package duke.task;

/**
 * Abstract class that is extended by Deadline, Event and Todo.
 * It contains methods to obtain/alter information about the Task object.
 * 
 * @author Gu Geng
 */
public abstract class Task {
    private String content;
    private boolean status;

    /**
     * Initialises the Task with the given information.
     * 
     * @param content A String that contains information that can possibly create a Task Object.
     */
    Task(String content) {
        this.content = content;
        this.status = false;
    }
    
    public String getContent() {
        return this.content;
    }

    public boolean getStatus() {
        return this.status;
    }

    /**
     * Sets the Task as done.
     */
    public void doneTask() {
        this.status = true;
    }

    /**
     * Overrides the toString method.
     *
     * @return A String representation of the Task object in specified format. 
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.status ? "x" : " ", this.content);
    }
    
    abstract public String record();
    abstract public boolean hasSchedule();
    abstract public String getType();

}
