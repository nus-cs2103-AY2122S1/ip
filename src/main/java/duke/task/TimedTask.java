package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.TimedTaskDateInputException;
import duke.formatter.DateTimeFormatCreator;

/**
 * A type of Task which represents Tasks which can store a datetime string.
 */
public abstract class TimedTask extends Task {

    /** A class level constant to represent the input date format accepted by Duke. */
    public static final String TIMED_TASK_YEAR_FORMAT = "yyyy-MM-dd";

    /** A class level constant to represent the input time format accepted by Duke. */
    public static final String TIMED_TASK_TIME_FORMAT = "HH:mm";

    /** A class level constant to represent the output date format which will be stored by Duke. */
    public static final String TIMED_TASK_MONTH_FORMAT = "MMM d yyyy";

    private final LocalDateTime dateTime;

    TimedTask(String name,
              String dateTime,
              TaskType taskType,
              boolean isCompleted) throws TimedTaskDateInputException {
        super(name, taskType, isCompleted);
        try {
            this.dateTime = LocalDateTime.parse(dateTime.trim(), DateTimeFormatCreator.format());
        } catch (DateTimeParseException e) {
            throw new TimedTaskDateInputException();
        }
    }

    protected abstract String getDateTimeString();

    protected String getDateTimeInternal() {
        return this.dateTime.toLocalDate().format(DateTimeFormatter.ofPattern(TIMED_TASK_YEAR_FORMAT))
                + " "
                + this.dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /**
     * Returns the datetime formatted to use the output format as indicated by TIMED_TASK_MONTH_FORMAT.
     * @return a datetime string.
     */
    public String getDateTime() {
        return this.dateTime.toLocalDate().format(DateTimeFormatter.ofPattern(TIMED_TASK_MONTH_FORMAT))
                + " "
                + this.dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /**
     * Returns a label which will be used to store the task after the application has exited.
     * @return a label
     */
    @Override
    public String getLabel() {
        return this.getName() + "|" + this.getDateTimeInternal();
    }

    /**
     * Returns the details of the Timed Task which will be displayed to the User Interface.
     * @return the Timed Task details.
     */
    @Override
    public String getDetails() {
        String checkbox = getCheckBox();
        String details = getTaskTypeString() + checkbox + " " + this.getName();
        return details + getDateTimeString();
    }
}
