package duke.task;

/**
 * Exists to provide a parent class to Event, Deadline, and ToDo classes.
 *
 * @author Leong Hong Fai
 */
public abstract class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Marks current task as completed.
     */
    public void setCompleted() {
        this.isCompleted = true;
    }


    /**
     * Indicates task object's completion using an 'X' or a blank space ' '.
     *
     * @return X or space depending on completion status of the Task object.
     */
    public String getStatusIcon() {
        return (this.isCompleted
                ?"X"
                :" ");
    }


    /**
     * Represents Task in a String format
     *
     * @return A string consisting of the information of the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }

    public String getName() {
        return this.name;
    }
}