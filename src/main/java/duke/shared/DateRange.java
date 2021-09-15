package duke.shared;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.constants.Constants;

/**
 * Encapsulates a pair of start datetime and end datetime.
 */
public class DateRange {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Creates a DateRange object with the given start and end datetime.
     *
     * @param start Start datetime.
     * @param end   End datetime.
     * @throws DukeException When start datetime occurs after end datetime.
     */
    public DateRange(LocalDateTime start, LocalDateTime end) throws DukeException {
        this.start = start;
        this.end = end;
        if (isInvalid()) {
            throw new InvalidDateException("Start date cannot be later than end date.");
        }
    }

    /**
     * Creates a DateRange object with the given start and end datetime strings.
     *
     * @param start String representing start datetime.
     * @param end   String representing end datetime.
     * @return DateRange object.
     * @throws DukeException When start datetime occurs after end datetime.
     */
    public static DateRange createFromString(String start, String end) throws DukeException {
        try {
            return new DateRange(LocalDateTime.parse(start, Constants.Input.DATETIME_FORMATTER),
                    LocalDateTime.parse(end, Constants.Input.DATETIME_FORMATTER));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Date format not recognized.");
        }
    }

    /**
     * Returns a DateRange object created from a single string representing the
     * datetime range. The string can of the following formats: dd/MM/yyyy
     * HHmm-dd/MM/yyyy HHmm or dd/MM/yyyy HHmm-HHmm The latter occurs on a single
     * day.
     *
     * @param range String representing a range of dates.
     */
    public static DateRange createFromRange(String range) throws DukeException {
        String[] rangeArr = range.split(Constants.Input.DATE_RANGE_SEPARATOR);
        if (rangeArr.length != 2) {
            throw new InvalidDateException("Date format not recognized.");
        }
        String start = rangeArr[0];
        String datePart = rangeArr[0].split(" ")[0];
        String end = rangeArr[1];
        if (!end.contains(" ")) {
            // Here end only has the time part, so we append the date part
            end = String.format("%s %s", datePart, end);
        }

        return createFromString(start, end);
    }

    /**
     * Returns whether the date range is invalid.
     *
     * @return Whether the date range is invalid.
     */
    public boolean isInvalid() {
        return start.isAfter(end);
    }

    /**
     * Returns whether the given datetime occurs before the date range.
     *
     * @param dateTime Datetime.
     * @return Whether the given datetime occurs before the date range.
     */
    public boolean occursBefore(LocalDateTime dateTime) {
        return start.isAfter(dateTime);
    }

    /**
     * Returns whether the given date occurs in one of the days of the date range.
     *
     * @param date Date.
     * @return Whether the given date occurs in one of the days of the date range
     */
    public boolean occursDuringDay(LocalDate date) {
        return !start.toLocalDate().isAfter(date) && !end.toLocalDate().isBefore(date);
    }

    /**
     * Returns whether the given datetime occurs between (non-strictly) the date range.
     *
     * @param dateTime Datetime.
     * @return Whether the given datetime occurs between (non-strictly) the date range.
     */
    public boolean occursBetween(LocalDateTime dateTime) {
        return !start.isAfter(dateTime) && !end.isBefore(dateTime);
    }

    /**
     * Returns whether the given datetime occurs between the date range.
     *
     * @param dateTime Datetime.
     * @return Whether the given datetime occurs between the date range.
     */
    public boolean occursStrictlyBetween(LocalDateTime dateTime) {
        return start.isBefore(dateTime) && end.isAfter(dateTime);
    }

    /**
     * Returns whether the given datetime occurs after the date range.
     *
     * @param dateTime Datetime.
     * @return Whether the given datetime occurs after the date range.
     */
    public boolean occursAfter(LocalDateTime dateTime) {
        return end.isBefore(dateTime);
    }

    /**
     * Returns a string that is the stored representation of a date range.
     *
     * @return String representation of a date range to be stored.
     */
    public String toStorageString() {
        return String.format("%s%s%s", Constants.Input.DATETIME_FORMATTER.format(start),
                Constants.Input.DATE_RANGE_SEPARATOR, Constants.Input.DATETIME_FORMATTER.format(end));
    }

    @Override
    public String toString() {
        String start = Constants.Display.DATETIME_FORMATTER.format(this.start);
        String end = Constants.Display.DATETIME_FORMATTER.format(this.end);
        assert !isInvalid(); // can only print valid date ranges
        return String.format("between %s and %s", start, end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof DateRange) {
            DateRange d = (DateRange) o;
            return this.start.equals(d.start) && this.end.equals(d.end);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return GenericHelpers.combineHashCodes(start.hashCode(), end.hashCode());
    }
}
