package genie.tasks;

/**
 * Task class that handles a task and methods that manipulated the task
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;
    
    public enum Priority {
        HIGH, MEDIUM, LOW
    }


    public Task(String description, Priority priority) {
        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    /**
     * A Method to mark the task as completed
     */
    public void taskDone() {
        this.isDone = true;
    }

    /**
     * A method to get the status icon
     * 
     * @return the symbol that shows if an event is done or not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method to retrieve the priority of an task.
     * @return The priority level of a task.
     */
    public String getPriority() {
        String priorityAsString;
        
        if (this.priority == Priority.HIGH) {
            priorityAsString = "HIGH";
        } else if (this.priority == Priority.MEDIUM) {
            priorityAsString = "MEDIUM";
        } else {
            priorityAsString = "LOW";
        }
        return priorityAsString;
        
    } 

    /**
     * A method to get the number that indicated the status of a task
     * 
     * @return the symbol that shows if an event is done or not
     */
    public int getStatusNumber() {
        return (isDone ? 1 : 0); // mark done task with 1
    }

    /**
     * A method that provides the desired string representation
     *
     * @return the String representation to be written into the file
     */
    public String toStringForFile() {
        return String.format("%d - %s / " + priority, this.getStatusNumber(), this.description);
    }

    /**
     *
     * @return the String representation of a Deadline
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
