package bubbles.exceptions;

/**
 * A class that represents the exception that is thrown
 * when the description of the Task is empty.
 */
public class EmptyTaskException extends Exception {
    private String taskType;

    /**
     * A public constructor for the EmptyTaskException.
     * @param taskType The type of Task (ToDo, Deadline or Event).
     */
    public EmptyTaskException(String taskType) {
        super(taskType);
        this.taskType = taskType;
    }

    /**
     * Returns the String representation of the Exception.
     *
     * @return The String representation of the Exception.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a " + taskType + " cannot be empty. ☹";
    }
}
