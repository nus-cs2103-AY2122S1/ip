package duke.task;

/**
 * The Task class encapsulates a task.
 */
public class Task {

    /** The given task description. */
    protected String description;
    /** Boolean to represent if the task is done, true if done, false otherwise. */
    protected boolean isDone;

    /**
     * Constructor for initialising a Task.
     *
     * @param description The given task description.
     */
    public Task(String description) {
        assert description != " " : "The task must have a proper description";

        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return Description of the task.
     */
    public String getTaskDescription() {
        return this.description;
    }

    /**
     * Checks and returns the status of a task in the form of "X" or " ".
     *
     * @return If the task is done "X" will be returned, else " " will be returned.
     */
    public String getStatusIcon() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Indicates that the Task is done.
     */
    public void completeTask() {
        isDone = true;
    }

    /**
     * Checks the description of two tasks, the tasks are equal if they have the same description.
     *
     * @param task The task to compare with.
     * @return True if the tasks are equal, false otherwise.
     */
    @Override
    public boolean equals(Object task) {
        if (task instanceof Task) {
            Task newTask = (Task) task;
            return this.description.equals(newTask.description);
        }
        return false;
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return String representation of the Task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
