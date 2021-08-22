package duke.task;

/**
 * Abstract parent of all Task objects in the application.
 */

public abstract class Task {
    private boolean flag;
    protected String label;

    /**
     * Basic constructor, creates a task with the given label.
     * @param label The description the task is created with. Not allowed to be empty.
     */
    public Task(String label) {
        this.label = label;
        this.flag = false;
    }

    /**
     * Method used to mark the task as done.
     * @param flag Indicates whether the task is marked as done.
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * Getter for boolean flag.
     * @return the flag of the Task.
     */
    public boolean getFlag() {
        return flag;
    }

    /**
     * Getter for label.
     * @return the label of the task.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Getter for type. Implementation left to subclasses.
     * @return a capital letter in a string e.g. "D" for a Deadline Task.
     */
    public abstract String getType();

    /**
     * Getter for date. Not applicable for Todo tasks.
     * @return A String representing the date associated with the Task.
     */
    public abstract String getDate();

    /**
     * Override of toString to provide a user friendly String representation of Task objects.
     * @return a String representing the Task meant to be printed for the user.
     */
    @Override
    public String toString() {
        if (flag) {
            return "[X] " + label;
        } else {
            return "[ ] " + label;
        }
    }
}
