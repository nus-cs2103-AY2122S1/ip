package duke.task;

import java.time.LocalDate;

/**
 * Abstract parent of all Task objects in the application.
 */

public abstract class Task {
    protected String label;
    protected LocalDate date;
    private boolean isDone;

    /**
     * Basic constructor, creates a task with the given label.
     *
     * @param label The description the task is created with. Not allowed to be empty.
     */
    public Task(String label) {
        this.label = label;
        this.isDone = false;
    }

    /**
     * Method used to mark the task as done.
     *
     * @param isDone Indicates whether the task is marked as done.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Getter for boolean isDone.
     *
     * @return the isDone of the Task.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Getter for label.
     *
     * @return the label of the task.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Setter for label.
     *
     * @param label the new label of the task.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Getter for type. Implementation left to subclasses.
     *
     * @return a capital letter in a string e.g. "D" for a Deadline Task.
     */
    public abstract String getType();

    /**
     * Getter for date. Not applicable for Todo tasks.
     *
     * @return A String representing the date associated with the Task.
     */
    public abstract String getDate();

    /**
     * Setter for date. Not applicable for Todo tasks.
     */
    public abstract void setDate(LocalDate date);

    /**
     * Override of toString to provide a user friendly String representation of Task objects.
     *
     * @return a String representing the Task meant to be printed for the user.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + label;
        } else {
            return "[ ] " + label;
        }
    }
}
