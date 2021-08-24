/**
 * This class represents a task to be done in the TaskList.
 */
public class Task {
    private String taskName;
    private boolean state;

    public Task(String taskName) {
        this.taskName = taskName;
        this.state = false;
    }

    /**
     * Gives the type of the Task (i.e Event, ToDo, Deadline). To be overriden.
     *
     * @return empty string
     */
    public String type() {
        return "";
    }

    /**
     * Gives the description of the task.
     *
     * @return description of the task
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Shows the state of the task.
     *
     * @return the state of the task
     */
    public boolean getState() {
        return this.state;
    }

    /**
     * Gives a save-file friendly version of the task. To be overriden in child classes.
     *
     * @return save information
     */
    public String getSaveInfo() {
        return this.taskName;
    }

    /**
     * Marks the task as done.
     *
     * @param print determines if the task being marked as done should be printed.
     */
    public void doneTask(boolean print) {
        this.state = true;
        if (print) {
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + this);
        }
    }

    @Override
    public String toString() {
        return (state ? "[X] " : "[ ] ") +  this.taskName;
    }
}
