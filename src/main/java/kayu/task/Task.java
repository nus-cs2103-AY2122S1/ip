package kayu.task;

/**
 * Task class.
 *
 * This abstract class acts as a base for all tasks handled by {@link kayu.Kayu}.
 */
public abstract class Task {

    // Encoding variables.
    public static String SPLIT_TEMPLATE = " # ";
    public static String DONE = "1";
    public static String NOT_DONE = "0";
    
    private String description;

    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Sets isDone field to true.
     */
    public void markAsDone() {
        setDone(true);
    }

    /**
     * Generates a String icon whether Task class is done or not.
     *
     * @return String icon based on done status.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done Duke.task with X
    }

    /**
     * Provides the encoded string representation of this Task instance.
     * 
     * @return Encoded String representation of instance.
     */
    public String toEncodedString() {
        return ((isDone) ? DONE : NOT_DONE) + SPLIT_TEMPLATE + description;
    }    
    
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
