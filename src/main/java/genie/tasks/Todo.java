package genie.tasks;

/**
 * A To-do class that is a subclass of Tasks. 
 */
public class Todo extends Task {
    public Todo(String description, Priority priority) {
        super(description, priority);
    }


    public String toStringForFile() {
        return "T - " + super.getStatusNumber() + " - " + super.description + " - " + priority;
    }
    
    /**
     * @return the String representation of a to do task
     */
    @Override
    public String toString() {
        return priority + " [T]" + super.toString();
    }
}
