package duke.task;

import java.time.format.DateTimeParseException;

/**
 * The type Event that has a task description and a date the event occurs at.
 */
public class Event extends TimedTask {

    /** Identifying tag 'E' for Event task */
    private final String identifier = "E";


    /**
     * Instantiates a new Event.
     *
     * @param description the description of the task.
     * @param date        the date constraint of the task.
     * @param timeArgs    the time constraint of the task.
     * @throws DateTimeParseException exception thrown when invalid date and/or time is entered.
     */
    public Event(String description, String date, String... timeArgs) throws DateTimeParseException {
        super(description, date, timeArgs);

        assert date != null : "Date constraints cannot be null.";
        assert description != null : "Event description cannot be null.";
    }

    /**
     * Prints out Event task with an identifier, a done marker and the date/time specified.
     *
     * @return String
     */
    @Override
    public String toString() {
        String result = "[" + identifier + "]";
        result += super.toString();
        result += isDateAndTime
                ? " (at: " + dateTime.format(super.dateTimePattern) + ")"
                : " (at: " + date.format(super.datePattern) + ")";
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
