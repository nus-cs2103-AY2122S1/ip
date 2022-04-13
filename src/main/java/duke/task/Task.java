package duke.task;

import duke.Priority;

/**
 * An abstract class encapsulating a Task.
 *
 * @author Toh Wang Bin
 */
public abstract class Task {

    /**
     * The variants of the Tasks handled by Duke
     */
    public enum Tasks { DEADLINE, EVENT, TODO }

    //A description of the task
    private String name;
    //A boolean signifying if the task has been completed
    private boolean isDone;
    //Priority level of the task
    private Priority.PriorityLevel priorityLevel;

    /**
     * Constructor for a Task instance.
     *
     * @param name A description of the Task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Getter for the name of a Task.
     *
     * @return A string.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the isDone field of a Task.
     *
     * @return A boolean indicating whther the Task is completed.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the priority level of the Task instance.
     *
     * @param str The priority level of the Task.
     */
    public void setPriorityLevel(String str) throws IllegalArgumentException {
        switch (str) {
        case "low":
            priorityLevel = Priority.PriorityLevel.LOW;
            break;
        case "medium":
            priorityLevel = Priority.PriorityLevel.MEDIUM;
            break;
        case "high":
            priorityLevel = Priority.PriorityLevel.HIGH;
            break;
        case "asap":
            priorityLevel = Priority.PriorityLevel.ASAP;
            break;
        //last case is none, or no priority assigned.
        case "none":
            //nothing
            break;
        default:
            //invalid string entered, throw exception.
            throw new IllegalArgumentException("Task.setPriorityLevel: Bad string entered.");
        }
    }

    /**
     * Getter for the priorityLevel field of a Task.
     *
     * @return The PriorityLevel as needed.
     */
    public Priority.PriorityLevel getPriorityLevel() {
        return priorityLevel;
    }

    /**
     * Sets the Task instance as completed.
     */
    public void setCompleted() {
        this.isDone = true;
    }

    /**
     * Returns the String representation of the Task instance.
     *
     * @return A String representing the Task instance.
     */
    public abstract String toString();

    /**
     * Returns a string representation of the Task instance optimised for
     * saving in the file.
     *
     * @return A String representing the Task instance.
     */
    public abstract String toDataString();

}
