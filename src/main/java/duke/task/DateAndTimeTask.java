package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * A Task with a Date and Time.
 * @author Thomas Hogben
 */
public abstract class DateAndTimeTask extends Task {
    private LocalDate date;
    private LocalTime time;

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
        setDateAndTime(input.substring(i + splitterKey.length()));
        assertDateAndTime();
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
        setDateAndTime(dateAndTime);
        assertDateAndTime();
    }

    /**
     * @param dateAndTime Expected format: "yyyy-MM-dd HHmm"
     * @return A LocalDateTime encapsulating the provided date and time.
     * @throws DukeException If the format of the date and time is incorrect.
     */
    public void setDateAndTime(String dateAndTime) throws DukeException {
        String[] splitDateAndTime = dateAndTime.split(" ");
        setDate(splitDateAndTime[0]);
        setTime(splitDateAndTime[1]);
        assertDateAndTime();
    }

    public void setDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.date = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw DukeException.INVALID_DATE;
        }
    }

    public void setTime(String time) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
            this.time = LocalTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            throw DukeException.INVALID_TIME;
        }
    }

    /**
     * @return The save string of this task. Incomplete, to be completed by child classes.
     */
    @Override
    public String getSave() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String dateStr = date.format(dateFormatter);
        String timeStr = time.format(timeFormatter);

        return super.getSave() + "|" + dateStr + " " + timeStr;
    }

    /**
     * @return The display string of this task. Incomplete; to be completed by child classes.
     */
    @Override
    public String toString() {
        assertDateAndTime();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d LLL yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h.mma");
        String result = super.toString();

        String dateStr = date.format(dateFormatter);
        String timeStr = time.format(timeFormatter);
        result += " (" + dateStr + " " + timeStr + ")";

        return result;
    }

    private void assertDateAndTime() {
        assert date != null : "Date of task cannot be null";
        assert time != null : "Time of task cannot be null";
    }
}
