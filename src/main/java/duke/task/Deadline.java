package duke.task;

/**
 * A class to handle Deadline tasks
 */
public class Deadline extends TaskWithDateTime {
    /**
     * Initializes an instance of Deadline class with task type, task description,
     * and the date/time by which the task has to be completed.
     * @param type Type of task
     * @param description Task description
     * @param dateTimeInput Date/time by whuch the tas has to be completed
     */
    public Deadline(TaskType type, String description, String dateTimeInput) {
        super(type, description, dateTimeInput);
    }

    /**
     * Initializes an instance of Deadline class with task type, task description,
     * date/time by which the task has to be completed and whether the task has been done.
     * @param type Type of task
     * @param description Task description
     * @param dateTimeInput Date/time by whuch the tas has to be completed
     * @param isDone A flag to indicate if the task has been done
     */
    public Deadline(TaskType type, String description, String dateTimeInput, boolean isDone) {
        super(type, description, dateTimeInput, isDone);
    }

    /**
     * Gets the date/time information by which the task is to be completed.
     * @return Date/time information
     */
    @Override
    public String dateTimeInfo() {
        return "(by: " + super.getDateTimeOutput() + ")";
    }

    /**
     * Gets a string representation of the task.
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        return "[" + TaskType.DEADLINE.getAbbr() + "] " + super.toString();
    }
}
