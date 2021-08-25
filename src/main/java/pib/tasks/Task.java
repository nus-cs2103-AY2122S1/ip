package pib.tasks;

import pib.Ui;
import pib.pibexception.PibException;

/**
 * Task is an abstract parent class of other specific types of tasks
 */
public abstract class Task {
    private String description;
    private int isDone;

    /**
     * Public constructor to store task description and set isDone to 0 (false)
     * Prints success message to user
     *
     * @param description The description of the task
     * @param printMessage Boolean to indicate whether to print the success message after each Task is added
     */
    public Task(String description, boolean printMessage) {
        this.description = description.trim();
        isDone = 0;
        if (printMessage) {
            Ui.printAddSuccess(description);
        }
    }

    /**
     * Public constructor to store task description and isDone
     * Prints success message to user
     *
     * @param description The description of the task
     * @param isDone Initialise the isDone field when calling the constructor
     * @param printMessage Boolean to indicate whether to print the success message after each Task is added
     */
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
     *
     * @throws PibException when user tries to call this method on an task that is already marked as done
     */
    public void markAsDone() throws PibException {
        if (this.isDone == 1) {
            throw new PibException("already-markedasdone");
        } else {
            this.isDone = 1;
            Ui.printMarkAsDoneSuccess(description);
        }
    }

    /**
     * Getter for description
     *
     * @return description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for isDone
     *
     * @return isDone value 0 (false) or 1 (true)
     */
    protected int getIsDone() {
        return isDone;
    }

    /**
     * Abstract method to convert each task into a string to be saved in the .txt file.
     *
     * @return String representation for saving data of a task
     */
    public abstract String toDataString();

    private String getStatusIcon() {
        return (isDone == 1 ? "X" : " ");
    }
}

