package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The type Timed tasks.
 */
public abstract class TimedTask extends Task {

    /** The formatting patterns for Tasks with date (and time) inputs. */
    protected final DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern("MMM d yyyy hh:mma");
    /** The formatting patterns for Tasks with date inputs. */
    protected final DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("MMM d yyyy");
    /** Stores Date specified by user */
    protected LocalDate date;
    /** Stores Date and Time if both are specified by user */
    protected LocalDateTime dateTime;
    /** Stores Time specified by user. Defaults to 00:00 */
    protected LocalTime time = LocalTime.parse("00:00");
    /** Boolean flag to check if both Date and Time are specified by user */
    protected boolean isDateAndTime = false;

    /**
     * Instantiates a timed task.
     *
     * @param description the description for the task.
     * @param dateString  the date constraint for the task in String.
     * @param timeArgs    optional time constraint for the task in String.
     * @throws DateTimeParseException exception thrown when invalid date and/or time is entered.
     */
    public TimedTask(String description, String dateString, String... timeArgs) throws DateTimeParseException {
        super(description);
        assert dateString != null : "Date constraint cannot be null.";
        scheduleTask(dateString, timeArgs);
    }

    /**
     * Schedule task to occur at a certain date and/or time.
     *
     * @param dateString the date constraint for the task in String.
     * @param timeArgs   optional time constraint for the task in String.
     * @throws DateTimeParseException exception thrown when invalid date and/or time is entered.
     */
    public void scheduleTask(String dateString, String... timeArgs) throws DateTimeParseException {
        if (timeArgs.length > 0) {
            scheduleDateTime(dateString, timeArgs);
            return;
        }
        scheduleDate(dateString);
    }

    /**
     * Helps to schedule a task to occur at a specific date.
     *
     * @param dateString the date constraint for the task in String.
     * @throws DateTimeParseException exception thrown when invalid date is entered.
     */
    private void scheduleDate(String dateString) throws DateTimeParseException {
        date = LocalDate.parse(dateString);
        dateTime = LocalDateTime.of(date, time);
    }

    /**
     * Helps to schedule a task to occur at a specific date.
     *
     * @param dateString the date constraint for the task in String.
     * @param timeArgs the time constraint for the task in String.
     * @throws DateTimeParseException exception thrown when invalid date is entered.
     */
    private void scheduleDateTime(String dateString, String... timeArgs) throws DateTimeParseException {
        date = LocalDate.parse(dateString);
        time = LocalTime.parse(timeArgs[0]);
        dateTime = LocalDateTime.of(date, time);
        isDateAndTime = true;
    }
}
