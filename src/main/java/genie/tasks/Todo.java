package genie.tasks;

/**
 * A child class of a task that represents a to do task that need to be done.
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
