package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.TimedTaskDateInputException;
import duke.formatter.DateTimeFormatCreator;

public abstract class TimedTask extends Task {

    public static final String TIMED_TASK_YEAR_FORMAT = "yyyy-MM-dd";

    public static final String TIMED_TASK_MONTH_FORMAT = "MMM d yyyy";

    public static final String TIMED_TASK_TIME_FORMAT = "HH:mm";

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

    public String getDateTime() {
        return this.dateTime.toLocalDate().format(DateTimeFormatter.ofPattern(TIMED_TASK_MONTH_FORMAT))
                + " "
                + this.dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    @Override
    public String getLabel() {
        return this.getName() + "|" + this.getDateTimeInternal();
    }

    @Override
    public String getDetails() {
        String checkbox = getCheckBox();
        String details = getTaskTypeString() + checkbox + " " + this.getName();
        return details + getDateTimeString();
    }
}
