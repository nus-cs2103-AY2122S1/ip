package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * A Task with a Date and Time.
 * @author Thomas Hogben
 */
public abstract class DateAndTimeTask extends Task {
    private LocalDateTime dateTime;

    /**
     * @param input The input string to create the task.
     * @param splitterKey The key that splits the input string into the task description
     *                    and the task date and time. Should include spaces on both ends.
     * @throws DukeException Thrown if the any part of the input string is invalid.
     */
    public DateAndTimeTask(String input, String splitterKey) throws DukeException {
        if (input.length() == 0) {
            throw DukeException.BLANK_DESCRIPTION;
        }
        int i = input.indexOf(splitterKey);
        if (i < 0) {
            throw DukeException.BLANK_DATE_AND_TIME;
        } else if (i == 0) {
            throw DukeException.BLANK_DESCRIPTION;
        }
        setDescription(input.substring(0, i));
        dateTime = parseDateAndTime(input.substring(i + splitterKey.length()));
    }

    /**
     * Creates a DateAndTimeTask manually. Used for loading saves.
     *
     * @param description The description of the task.
     * @param dateAndTime The date and time of the task.
     * @param isDone Whether the task is done.
     * @throws DukeException Thrown if any part of the input string is invalid.
     */
    public DateAndTimeTask(String description, String dateAndTime, boolean isDone) throws DukeException {
        super(description, isDone);
        dateTime = parseDateAndTime(dateAndTime);
    }

    /**
     * @param dateAndTime Expected format: "yyyy-MM-dd HHmm"
     * @return A LocalDateTime encapsulating the provided date and time.
     * @throws DukeException If the format of the date and time is incorrect.
     */
    private LocalDateTime parseDateAndTime(String dateAndTime) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateAndTime, formatter);
        } catch (DateTimeParseException e) {
            throw DukeException.INVALID_DATE_AND_TIME;
        }
    }

    /**
     * @return The save string of this task. Incomplete, to be completed by child classes.
     */
    @Override
    public String getSave() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return super.getSave() + "|" + dateTime.format(formatter);
    }

    /**
     * @return The display string of this task. Incomplete; to be completed by child classes.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d LLL yyyy h.mma");
        String result = super.toString();
        if (dateTime != null) {
            result += " (" + dateTime.format(formatter) + ")";
        }
        return result;
    }
}
