/**
 * Task class used as Parent class for Deadline, ToDo, and Event.
 * Contains methods that
 * (i) gets the corresponding status icon of the task
 * (ii) marks the existing instance of Task as done
 * (iii) gets task description attribute
 * (iv) gets task isDone attribute
 * (v) overrides the default toString method to display the status and description
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     *
     * @param description the overview of the task for a particular instance
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * The getStatusIcon() method returns the string representation of whether
     * the task isDone.
     *
     * @return String type object that displays an "[X]" if the task is done, else
     * "[ ]" is displayed.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]"); //mark done task with an X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Overriding toString method to display the relevant information
     *
     * @return String type object that includes the status icon and
     * task description
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
