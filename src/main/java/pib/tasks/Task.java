package pib.tasks;

import pib.Ui;
import pib.pibexception.PibException;

/**
 * Task is a parent class of other specific types of tasks
 */
public abstract class Task {
    private String description;
    private int isDone;

    /**
     * @param description The description of the task
     */
    public Task(String description, boolean printMessage) {
        this.description = description.trim();
        isDone = 0;
        if (printMessage) {
            Ui.printAddSuccess(description);
        }
    }

    public Task(String description, int isDone, boolean printMessage) {
        this.description = description.trim();
        this.isDone = isDone;
        if (printMessage) {
            Ui.printAddSuccess(description);
        }
    }

    /**
     * A public toString method to return the task description with the checkbox for toggling completion
     *
     * @return the string representation of a task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Method to toggle the isDone variable, to display to the user if a task is marked as done or not
     */
    public void markAsDone() throws PibException {
        if (this.isDone == 1) {
            throw new PibException("already-markedasdone");
        } else {
            this.isDone = 1;
            Ui.printMarkAsDoneSuccess(description);
        }
    }

    public String getDescription() {
        return this.description;
    }

    protected int getIsDone() {
        return isDone;
    }

    public abstract String toDataString();

    private String getStatusIcon() {
        return (isDone == 1 ? "X" : " ");
    }
}

