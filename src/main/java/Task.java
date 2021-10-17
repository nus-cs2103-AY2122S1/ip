import java.time.LocalDate;

/**
 * Class Task that is the superclass of Todo, Event and Deadline
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class
     * @param description takes in the task name
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to show whether task is done or not
     * @return String X that indicates it is done or blank to indicate otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method that returns description
     * @return string of task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Shows type of task
     * @return untyped as a task is not assigned
     */
    public String getTask() {
        return "untyped";
    }

    /**
     * Method to get date of task if applicable
     * @return string representing date
     */
    public String getDate() {
        return null;
    }

    /**
     * Gets the numbers of the date if applicable
     * @return string with date numbers
     */
    public String getDateNum() {
        return null;
    }

}
