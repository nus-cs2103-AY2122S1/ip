package pib.tasks;

import pib.utility.Ui;
import pib.pibexception.PibException;

/**
 * Task is an abstract parent class of other specific types of tasks
 */
public abstract class Task {
    private String description;
    private int isDone;

    /**
     * Constructs a new task with description and set isDone to 0 (false), and prints success message to user
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
     * Constructs a new task with description and isDone, and prints success message to user
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
     * Returns a string containing the task description and the checkbox for toggling completion
     *
     * @return the string representation of a task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Toggles the checkbox for the isDone variable
     *
     * @throws PibException when user tries to call this method on a task that is already marked as done
     * @return String containing response to be printed to user
     */
    public String markAsDone() throws PibException {
        if (isDone == 1) {
            throw new PibException("already-markedasdone");
        }
        isDone = 1;
        return Ui.printMarkAsDoneSuccess(description);
    }

    /**
     * Getter for description
     *
     * @return description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the task description to a new string
     *
     * @param s new string to update the description with
     * @throws PibException when s is blank
     */
    public String editDescription(String s) throws PibException {
        if (s.isBlank()) {
            throw new PibException("empty-new-value");
        }
        description = s;
        return Ui.printUpdateSuccessful();
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
     * Converts each task into a string to be saved in the .txt file (Abstract Method)
     *
     * @return String representation for saving data of a task
     */
    public abstract String toDataString();

    private String getStatusIcon() {
        return (isDone == 1 ? "X" : " ");
    }
}

