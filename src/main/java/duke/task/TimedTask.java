package duke.task;

import duke.DateTimeFormatCreator;
import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Tasks with local dateTime features (parent of Deadline and Event)
 */
public abstract class TimedTask extends Task {

    /** The datetime to be stored.*/
    private final LocalDateTime dateTime;

    /**
     * Constructor for TimedTask
     * @param name Name of task.
     * @param dateTime Time of task.
     * @param taskType Type of Task.
     * @param isCompleted Completion status.
     * @throws DukeException when an incorrect date was given.
     */
    TimedTask(String name, String dateTime, TaskType taskType, boolean isCompleted) throws DukeException {
        super(name, taskType, isCompleted);
        try{
            this.dateTime = LocalDateTime.parse(dateTime.trim(), DateTimeFormatCreator.format());
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect form of date input. Please try yyyy-MM-dd HH:mm instead.");
        }
    }

    /**
     * Abstract method that returns the string representation of the dateTime variable.
     * @return dateTime
     */
    protected abstract String dateTimeString();

    /**
     * Method that returns the string which is to be used by the Duke internally (not for rendering).
     * @return String
     */
    protected String getDateTimeInternal() {
        return this.dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + " " + this.dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /**
     * Method that returns the string which is to be rendered by UserInterface.
     * @return
     */
    public String getDateTime() {
        return this.dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + this.dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /**
     * The label to be stored
     * @return label
     */
    @Override
    public String getLabel() {
        return this.getName() + "|" + this.getDateTimeInternal();
    }

    /**
     *  The details of the task.
     * @return the details.
     */
    @Override
    public String details() {
        String checkbox = "[" + ( getCompleted() ? "X" : " ") + "]";
        String details = taskTypeString() + checkbox + " " + this.getName();
        return details + dateTimeString();
    }
}
