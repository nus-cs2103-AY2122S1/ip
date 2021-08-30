package duke.tasks;

import duke.date.Date;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongTimeFormatException;

import java.util.Optional;
import duke.status.Status;

/**
 * Abstract class that outlines the base structure of a task
 */
public abstract class Task {
    protected final String taskDescription;
    protected final String status;
    protected final Optional<Date> date;

    /**
     * Initializes a new task object with description 
     * of Task and its placement if any.
     * 
     * @param desc String of description of task
     * @param hasDateTime boolean if the task has a date time attribute
     * @param taskDirective String of the task with a placment "at" or "by"
     * @throws WrongDateFormatException if format of date in a task is wrong
     * @throws WrongTimeFormatException if format of time in a task is wrong
     */
    public Task(String desc, boolean hasDateTime, String taskDirective) throws WrongDateFormatException, WrongTimeFormatException {
        this.taskDescription = desc;
        this.status = Status.NOT_COMPLETED.getStatus();
        this.date = this.getDateAndTime(desc, hasDateTime, taskDirective);
    }

    private Optional<Date> getDateAndTime(String desc, boolean hasDateTime, String taskDirective) throws WrongDateFormatException, WrongTimeFormatException {
        if (hasDateTime) {
            String[] descArray = desc.split("/" + taskDirective);
            String dateAndTime = descArray[1].trim();
            Date outputDate = new Date(dateAndTime);
            return Optional.ofNullable(outputDate);
        }
        return Optional.empty();
    }

    /**
     * Overloaded task constructor to update
     * completion status of a task
     * 
     * @param desc String description of the task
     * @param newStatus String updated staus of task
     * @param date Optional date atribute of task
     */
    public Task(String desc, String newStatus, Optional<Date> date) {
        this.taskDescription = desc;
        this.status = newStatus;
        this.date = date;
    }

    public abstract String getOriginalFormatForStorage();

    public abstract Task updateStatus(String newStatus);
}
