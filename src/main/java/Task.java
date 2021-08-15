/**
 *  This class represents the tasks in a list
 * @author Ryan Tian Jun
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    // return task status
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done
     *
     * @return returns true if it was successful
     */
    public boolean markAsDone() {
        if (!isDone) {
            isDone = true;
            return true;
        } else {
            return false;
        }
    }

    // return task description
    public String getDescription() {
        return description;
    }
}
