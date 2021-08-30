package duke.tasks;

import duke.date.Date;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongTimeFormatException;

import java.util.Optional;
import duke.status.Status;

public abstract class Task {
    protected final String taskDescription;
    protected final String status;
    protected final Optional<Date> date;

    public Task(
            String desc, boolean hasDateTime,
            String taskDirective) throws WrongDateFormatException,
            WrongTimeFormatException {
        this.taskDescription = desc;
        this.status = Status.NOT_COMPLETED.getStatus();
        this.date = this.getDateAndTime(desc, hasDateTime, taskDirective);
    }

    private Optional<Date> getDateAndTime(
            String desc, boolean hasDateTime,
            String taskDirective) throws WrongDateFormatException,
            WrongTimeFormatException {
        if (hasDateTime) {
            String[] descArray = desc.split("/" + taskDirective);
            String dateAndTime = descArray[1].trim();
            Date outputDate = new Date(dateAndTime);
            return Optional.ofNullable(outputDate);
        }
        return Optional.empty();
    }

    public Task(
            String desc, String newStatus,
            Optional<Date> date) {
        this.taskDescription = desc;
        this.status = newStatus;
        this.date = date;
    }

    public abstract String getOriginalFormatForStorage();

    public abstract Task updateStatus(String newStatus);
}
