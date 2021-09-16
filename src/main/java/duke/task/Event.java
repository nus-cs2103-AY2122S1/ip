package duke.task;

/**
 * A class to handle Event tasks
 */
public class Event extends TaskWithDateTime {
    /**
     * Initializes an instance of Event class with task type, description,
     * and the date/time at which the task is to be performed.
     * @param type Type of task
     * @param description Task description
     * @param dateTimeInput Date/time at which the task is to be performed
     */
    public Event(TaskType type, String description, String dateTimeInput) {
        super(type, description, dateTimeInput);
    }

    /**
     * Initializes an instance of Event class with task type, description,
     * date/time at which the task is to be performed and whether the task
     * has been completed.
     * @param type Type of task
     * @param description Task description
     * @param dateTimeInput Date/time at which the task is to be performed
     * @param isDone A flag to indicate if the task has been completed
     */
    public Event(TaskType type, String description, String dateTimeInput, boolean isDone) {
        super(type, description, dateTimeInput, isDone);
    }

    /**
     * Gets date/time info of the task in output format.
     * @return date/time info
     */
    @Override
    public String dateTimeInfo() {
        return "(at: " + super.getDateTimeOutput() +")";
    }

    /**
     * Gets a string representation of a task with date/time.
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        return "[" + TaskType.EVENT.getAbbr() + "] " + super.toString();
    }
}
