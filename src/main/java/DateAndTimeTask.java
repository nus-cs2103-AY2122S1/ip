import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateAndTimeTask extends Task {
    private LocalDateTime dateTime;

    /**
     * @param input The input string to create the task.
     * @param splitterKey The key that splits the input string into the task description
     *                    and the task date and time. Should include spaces on both ends.
     * @throws DukeException Thrown if the any part of the input string is invalid.
     */
    public DateAndTimeTask(String input, String splitterKey) throws DukeException {
        if (input.length() <= 1) {
            throw new DukeException(DukeException.BLANK_DESCRIPTION);
        } else {
            int i = input.indexOf(splitterKey);
            if (i < 0) {
                throw new DukeException(DukeException.BLANK_DATE_AND_TIME);
            } else if (i <= 1) {
                throw new DukeException(DukeException.BLANK_DESCRIPTION);
            }
            this.setDescription(input.substring(1, i));
            this.dateTime = parseDateAndTime(input.substring(i + splitterKey.length()));
        }
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
        this.dateTime = parseDateAndTime(dateAndTime);
    }

    private LocalDateTime parseDateAndTime(String dateAndTime) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateAndTime, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.INVALID_DATE_AND_TIME);
        }
    }

    @Override
    public String getSave() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return super.getSave() + "|" + dateTime.format(formatter);
    }

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
