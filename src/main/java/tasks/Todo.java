package tasks;

/**
 * A To-do class that is a subclass of Tasks. 
 */
public class Todo extends Task {
    public Todo(String description, Priority priority) {
        super(description, priority);
    }

    /**
     * A method that overwrites the toStringForFile() method
     * in tasks
     * 
     * @return the String representation of a to-do task, to be written into the file
     */
    @Override
    public String toStringForFile() {
        return "T - " + super.toStringForFile();
    }

    /**
     * @return the String representation of a to do task
     */
    @Override
    public String toString() {
        return priority + " [T]" + super.toString();
    }
}
