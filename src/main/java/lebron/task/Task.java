package lebron.task;

/**
 * This class represents a Task the user gives to the bot.
 *
 * @author Nigel Tan
 */
public class Task {

    private final String name;
    private boolean isDone;

    /**
     * Constructor.
     *
     * @param name the name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * This method marks the task as true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * This method returns the status icon of the tast, X if true, blank if false.
     *
     * @return X if true, blank if false.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // markAsDone done task with X
    }

    /**
     * This method returns the name of the Task.
     *
     * @return name of the Task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the value representing if the task is done.
     *
     * @return 1 if the task is done, 0 if the task is not done.
     */
    public String getDoneValue() {
        return isDone ? "1" : "0";
    }

    /**
     * Produce the desired format for storing to file.
     *
     * @return the desired string.
     */
    public String getStringForFile() {
        return " | " + getDoneValue() + " | " + name;
    }

    @Override
    public String toString() {
        String format = String.format("[%s] ", this.getStatusIcon());
        return format + this.name;
    }

}
