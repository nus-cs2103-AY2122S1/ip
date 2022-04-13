package duke.tasks;

/**
 * Task class used as Parent class for Deadline, ToDo, and Event.
 * Contains methods that
 * (i) gets the corresponding status icon of the task.
 * (ii) marks the existing instance of Task as done.
 * (iii) gets task description attribute.
 * (iv) gets task isDone attribute.
 * (v) sets task isDone attribute to given value.
 * (vi) overrides the default toString method to display the status and description.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task
     *
     * @param description Overview of the task for a particular instance.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of whether the task isDone.
     *
     * @return String type object that displays an "[X]" if the task is done, else "[ ]" is displayed.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]"); //mark done task with an X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    protected void setIsDone(boolean done) {
        this.isDone = done;
        return;
    }

    /**
     * Formats the String returned by toString() in a more readable manner.
     * @return String object that represents formatted toString() output for ease of reading.
     */
    public abstract String formatString();

    /**
     * Returns String representation of specific tasks.
     *
     * @return String type object that includes the status icon and task description.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
