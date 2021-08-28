package duke.domain;

import java.time.LocalDateTime;
import java.util.List;

import duke.shared.DateHelpers;
import duke.shared.DateRange;

/**
 * Encapsulates a task taking place over a specified period of time.
 */
public class Event extends Task {
    public static final String TYPE_STRING = "E";
    private DateRange dateRange;

    /**
     * Creates incomplete event with given name and date range over which the event takes place.
     *
     * @param name      Name of event.
     * @param dateRange Date range over which event takes place.
     */
    public Event(String name, String dateRange) {
        super(name);
        // typeString = TYPE_STRING;
        this.dateRange = DateRange.createFromRange(dateRange);
    }

    /**
     * Creates event with given name, completion status and date range over which the event takes place.
     *
     * @param name      Name of event.
     * @param isDone    Whether event is to be marked complete upon creation.
     * @param dateRange Date range over which event takes place.
     */
    public Event(String name, boolean isDone, String dateRange) {
        super(name, isDone);
        this.dateRange = DateRange.createFromRange(dateRange);
    }

    /**
     * Returns whether the given day falls within the date range of the event. Time component is ignored.
     *
     * @param dateString String representing a day.
     * @return Whether the given day falls within the date range of the event.
     */
    public boolean isOccurringOnDay(String dateString) {
        return isOccurringOnDay(DateHelpers.parseDateString(dateString));
    }

    /**
     * Returns whether the given day falls within the date range of the event. Time component is ignored.
     *
     * @param dateTime A day.
     * @return Whether the given day falls within the date range of the event.
     */
    public boolean isOccurringOnDay(LocalDateTime dateTime) {
        return dateRange.occursDuringDay(dateTime.toLocalDate());
    }

    /**
     * Returns whether the given datetime falls (non-strictly) within the date range.
     *
     * @param dateTime Datetime.
     * @return Whether the given datetime falls (non-strictly) within the date range.
     */
    public boolean isOccurringBetween(LocalDateTime dateTime) {
        return dateRange.occursBetween(dateTime);
    }

    /**
     * Returns whether the given datetime falls strictly within the date range.
     *
     * @param dateTime Datetime.
     * @return Whether the given datetime falls strictly within the date range.
     */
    public boolean isOccurringStrictlyBetween(LocalDateTime dateTime) {
        return dateRange.occursStrictlyBetween(dateTime);
    }

    /**
     * Returns an Event object from a given array of fields. Effectively the inverse of storageFields.
     *
     * @return An Event object.
     */
    public static Task generateFromString(String[] fields) {
        int isDoneInt = Integer.parseInt(fields[1]);
        boolean isDone = isDoneInt == 1;
        String name = fields[2];
        String dateRange = fields[3];
        return new Event(name, isDone, dateRange);
    }

    @Override
    public List<String> storageFields() {
        List<String> fields = super.storageFields();
        fields.add(dateRange.toStorageString());

        return fields;
    }

    @Override
    public String toString() {
        String base = super.toString();
        String result = String.format("%s (at: %s)", base, dateRange);
        return result;
    }
}
