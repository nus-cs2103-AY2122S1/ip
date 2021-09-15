package duke.task;

import java.time.format.DateTimeParseException;

/**
 * The type Deadline that has a task description and a deadline date to complete the task by.
 */
public class Deadline extends TimedTask {

    /** Identifying tag 'D' for deadline task..*/
    private final String identifier = "D";


    /**
     * Instantiates a new Deadline.
     *
     * @param description the description of the task.
     * @param dateString  the date constraint of the task.
     * @param timeArgs    the time constraint of the task.
     * @throws DateTimeParseException exception thrown when invalid date and/or time is entered.
     */
    public Deadline(String description, String dateString, String... timeArgs) throws DateTimeParseException {
        super(description, dateString, timeArgs);

        assert date != null : "Date constraints cannot be null.";
        assert description != null : "Deadline description cannot be null.";
    }

    /**
     * Prints out Deadline task with an identifier, a done marker and the date/time specified.
     *
     * @return String.
     */
    @Override
    public String toString() {
        String result = "[" + identifier + "]";
        result += super.toString();
        result += (isDateAndTime
                ? " (by: " + dateTime.format(super.dateTimePattern) + ")"
                : " (by: " + date.format(super.datePattern) + ")");
        return result;
    }

    @Override
    public String toDatabaseString() {
        String result = identifier + "|";
        result += getStatus() ? "1|" : "0|";
        result += getDescription() + "|";
        result += isDateAndTime ? (date + "|" + time) : date;
        return result;
    }
}
