package petal.task;

import petal.components.Responses;
import java.time.LocalDate;

/**
 * The Task class that encapsulates a directive given
 * by the user to track a certain activity and may have
 * start/end timings.
 */
public abstract class Task {

    //Properties of a Task
    private final String description;
    private boolean isDone;

    /**
     * Formats string representation which is optimized for saving
     *
     * @return Formatted string representation
     */
    public abstract String strForSaving();

    /**
     * Returns whether the Task is a Timeable
     *
     * @return boolean True if Timeable, false if not Timeable
     */
    public abstract boolean isTimeable();

    /**
     * Constructor for the Task class
     *
     * @param description The description of the task
     * @param isDone  The boolean isDone, representing if the Task is done
     * @param localDate The date of the Task
     */
    public Task(String description, boolean isDone, LocalDate localDate) {
        this.description = firstLetter(description);
        this.isDone = isDone;
    }

    /**
     * Constructor for the Task class
     *
     * @param description The description of the task
     * @param isDone The boolean isDone, representing if the Task is done
     */
    public Task(String description, boolean isDone) {
        this.description = firstLetter(description);
        this.isDone = isDone;
    }

    /**
     * Returns whether a task is done in string representation
     *
     * @return "X" if done, " " if not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the task instance as done
     */
    public void taskDone() {
        System.out.println(Responses.LINE + "\nYou have completed the task: " + "'"
                                          + this.description + "'!"
                                          + "\nI am so happy for you!\n"
                                          + Responses.LINE);
        this.isDone = true;
    }

    /**
     * Capitalizes the first letter of the string
     *
     * @param str The string
     * @return String but with first letter capitalized
     */
    public String firstLetter(String str) {
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        return str;
    }

    /**
     * Overridden toString method for the Task class
     *
     * @return String representation of the Task object
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}
