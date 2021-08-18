/**
 * This class represents a Task the user gives to the bot.
 * @author Nigel Tan
 */
public class Task {
    private String name;
    private boolean isDone;
    private int position;

    /**
     * Constructor.
     * @param name the name of the task
     * @param pos the position of the task in the list
     */
    public Task(String name, int pos) {
        this.name = name;
        this.isDone = false;
        this.position = pos;
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
}
