package duke.task;

/**
 * Deadline Class to handle Deadline Tasks
 */
public class Deadline extends Task {
    protected String dueDate;

    /**
     * Constructor for Deadline Class
     *
     * @param name    name of the task
     * @param dueDate Due date of the task. Can be Date or Date/Time
     */
    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = TaskTime.convertDateTimeFormat(dueDate);
    }

    /**
     * Returns the string to be written in the txt file
     */
    @Override
    public String write() {
        return "D " + super.write() + " | " + this.dueDate;
    }

    /**
     * Returns the task for the list format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }
}
