/**
 * This class represents a Task the user gives to the bot.
 * @author Nigel Tan
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructor.
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * This method marks the task as true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * This method returns the status icon of the tast, X if true, blank if false.
     * @return X if true, blank if false
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This method returns the name of the Task
     * @return name of the Task
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        String format = String.format("[%s] ", this.getStatusIcon());
        return format + this.name;
    }
}
