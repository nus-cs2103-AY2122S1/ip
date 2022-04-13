package duke.tasks;

import duke.date.Date;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongEventOrDeadlineFormatException;
import duke.exceptions.WrongTimeFormatException;
import java.util.Optional;
import duke.status.Status;

/**
 * Abstract class that outlines the
 * base structure of a task.
 */
public abstract class Task {
    /**
     * Displays the task description as string.
     */
    protected final String taskDescription;

    /**
     * Completed or uncompleted status.
     */
    protected final String status;

    /**
     * Optional date if it exists.
     */
    protected final Optional<Date> date;

    /**
     * Initializes a new task object with description
     * of Task and its placement if any.
     *
     * @param desc String of description of task
     * @param hasDateTime boolean if the task has a date time attribute
     * @param taskDirective String of the task with a placement "at" or "by"
     * @throws WrongDateFormatException if format of date in a task is wrong
     * @throws WrongTimeFormatException if format of time in a task is wrong
     */
    public Task(
            String desc, boolean hasDateTime,
            String taskDirective) throws WrongDateFormatException,
            WrongTimeFormatException,
            WrongEventOrDeadlineFormatException {
        this.taskDescription = desc;
        this.status = Status.NOT_COMPLETED.getStatus();
        this.date = this.getDateAndTime(desc, hasDateTime, taskDirective);
    }

    private Optional<Date> getDateAndTime(
            String desc, boolean hasDateTime,
            String taskDirective) throws WrongDateFormatException,
            WrongTimeFormatException,
            WrongEventOrDeadlineFormatException {
        if (hasDateTime) {
            String[] descArray = desc.split("/" + taskDirective);
            if (descArray.length <= 1) {
                throw new WrongEventOrDeadlineFormatException();
            }
            String dateAndTime = descArray[1].trim();
            Date outputDate = new Date(dateAndTime);
            return Optional.ofNullable(outputDate);
        }
        return Optional.empty();
    }

    /**
     * Overloaded task constructor to update
     * completion status of a task.
     * @param desc String description of the task
     * @param newStatus String updated status of task
     * @param date Optional date attribute of task
     */
    public Task(
            String desc, String newStatus,
            Optional<Date> date) {
        this.taskDescription = desc;
        this.status = newStatus;
        this.date = date;
    }

    /**
     * Converts to appropriate format for storing of tasks.
     * @return String format of tasks.
     */
    public abstract String getOriginalFormatForStorage();

    /**
     * Changes the status of a task if it is marked
     * completed by the user.
     * @param newStatus String of new status task should be changed to.
     * @return Task with a status changed if its completed or not.
     */
    public abstract Task updateStatus(String newStatus);

    /**
     * Returns an optional date object.
     * @return Optional date
     */
    public Optional<Date> getOptionalDate() {
        return this.date;
    }
}
