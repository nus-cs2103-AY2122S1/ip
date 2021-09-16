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
        String[] processedInput = processInput(input, splitterKey);
        setDescription(processedInput[0]);
        setDateAndTime(processedInput[1]);
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
    }

    /**
     * @param dateAndTime Expected format: "yyyy-MM-dd HHmm"
     * @throws DukeException If the format of the date and time is incorrect.
     */
    public void setDateAndTime(String dateAndTime) throws DukeException {
        String[] splitDateAndTime = dateAndTime.split(" ");
        setDate(splitDateAndTime[0]);
        setTime(splitDateAndTime[1]);
    }

    /**
     * @param date Expected format: "yyyy-MM-dd"
     * @throws DukeException If the format of the date is incorrect.
     */
    public void setDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.date = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw DukeException.INVALID_DATE;
        }
    }

    /**
     * @param time Expected format: "HHmm"
     * @throws DukeException If the format of the time is incorrect.
     */
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
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d LLL yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h.mma");
        String result = super.toString();

        if (date != null && time != null) {
            String dateStr = date.format(dateFormatter);
            String timeStr = time.format(timeFormatter);
            result += " (" + dateStr + " " + timeStr + ")";
        }

        return result;
    }

    /**
     * Casts a given task to a DateAndTimeTask.
     *
     * @param task The task to be casted.
     * @return The casted task.
     * @throws DukeException If the task is not an instance of DateAndTimeTask.
     */
    public static DateAndTimeTask cast(Task task) throws DukeException {
        DateAndTimeTask result;

        if (task instanceof DateAndTimeTask) {
            result = (DateAndTimeTask) task;
        } else {
            throw DukeException.INVALID_TASK_TYPE;
        }

        return result;
    }

    /**
     *
     * @param input The input string to create the task.
     * @param splitterKey The key that splits the input string into the task description
     *                    and the task date and time. Should include spaces on both ends.
     * @return A String[] with the description as element 0 and the date and time string
     *         as element 1.
     * @throws DukeException Thrown if the any part of the input string is invalid.
     */
    private String[] processInput(String input, String splitterKey) throws DukeException {
        if (input.length() == 0) {
            throw DukeException.BLANK_DESCRIPTION;
        }

        int i = input.indexOf(splitterKey);

        if (i < 0) {
            throw DukeException.BLANK_DATE_AND_TIME;
        } else if (i == 0) {
            throw DukeException.BLANK_DESCRIPTION;
        }

        return input.split(splitterKey);
    }
}
