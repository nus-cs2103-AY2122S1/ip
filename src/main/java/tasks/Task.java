package tasks;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The Task class implements the tasks to be tracked by the
 * Duke assistant.
 */
public class Task {

    /** Description of the task */
    private final String task;

    /** Status of the task */
    private String isDone;

    /**
     * Constructs a Task.
     *
     * @param description the input string to describe the task
     */
    public Task(String description) {
        this.task = description;
        this.isDone = "[ ]";
    }

    /**
     * Sets a task as complete by changing
     * the String representation and display a confirmation message.
     *
     * @return the message in response to the action
     */
    public String setIsDone() {
        String result;
        if (this.isDone.equals("[ ]")) {
            this.isDone = "[X]";
            result = "Well done! The task is completed!";
        } else {
            result = "You have already completed this task before!";
        }
        return "     " + result + "\n" + "     " + getType() + isDone + " " + getDescription();
    }

    /**
     * Marks a task as complete by changing
     * the String representation without displaying confirmation message.
     */
    public void markAsDone() {
        this.isDone = "[X]";
    }

    /**
     * Retrieves the status of the task
     * whether it is complete or not.
     *
     * @return the string representation of the task's state
     */
    public String getStatus() {
        return this.isDone;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return the String that is the description of the task
     */
    public String getDescription() {
        return this.task;
    }

    /**
     * Retrieves the information of the type of task.
     *
     * @return the String description of the type of task
     */
    public String getType() {
        return "regular";
    }

    /**
     * Retrieves the information on the format of the task to be saved.
     *
     * @return the String representation of how the task will be saved
     */
    public String getSaveFormat() {
        return "regular";
    }

    /**
     * Retrieves the information on the date associated with task.
     *
     * @return the date associated with task in as LocalDate if applicable
     */
    public LocalDate getLocalDate() {
        return null;
    }

    /**
     * Sets the date as a valid date.
     * @param localDate the specific valid date to store
     */
    public void setLocalDate(LocalDate localDate) {}

    /**
     * Sets the time as a valid time.
     * @param localTime the specific valid time to store
     */
    public void setLocalTime(LocalTime localTime) {}

}
