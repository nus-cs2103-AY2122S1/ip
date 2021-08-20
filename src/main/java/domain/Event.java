package domain;

import java.util.List;
import java.time.LocalDateTime;

import shared.DateHelpers;
import shared.DateRange;

/**
 * Encapsulates a task taking place over a specified period of time.
 */
public class Event extends Task {
    public static final String TYPE_STRING = "E";
    private DateRange dateRange;

    public Event(String name, String dateRange) {
        super(name);
        // typeString = TYPE_STRING;
        this.dateRange = DateRange.createFromRange(dateRange);
    }

    public Event(String name, boolean isDone, String dateRange) {
        super(name, isDone);
        this.dateRange = DateRange.createFromRange(dateRange);
    }

    public boolean isOccurringOnDay(String dateString) {
        return isOccurringOnDay(DateHelpers.parseDateString(dateString));
    }

    public boolean isOccurringOnDay(LocalDateTime dateTime) {
        return dateRange.occursDuringDay(dateTime.toLocalDate());
    }

    public boolean isOccurringBetween(LocalDateTime dateTime) {
        return dateRange.occursBetween(dateTime);
    }

    public boolean isOccurringStrictlyBetween(LocalDateTime dateTime) {
        return dateRange.occursStrictlyBetween(dateTime);
    }

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
