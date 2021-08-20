package domain;

import java.time.LocalDateTime;

import shared.DateHelpers;
import shared.DateRange;

/**
 * Encapsulates a task taking place over a specified period of time.
 */
public class Event extends Task {
    private DateRange dateRange;

    public Event(String name, String dateRange) {
        super(name);
        typeString = "E";
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

    @Override
    public String toString() {
        String base = super.toString();
        String result = String.format("%s (at: %s)", base, dateRange);
        return result;
    }
}
