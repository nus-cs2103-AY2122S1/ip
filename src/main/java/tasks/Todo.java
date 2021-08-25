package tasks;

/**
 * A To-do class that is a subclass of Tasks. 
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
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
     * @return the String representation of a Deadline
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
