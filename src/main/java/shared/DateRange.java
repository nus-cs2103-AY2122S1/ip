package shared;

import java.time.LocalDate;
import java.time.LocalDateTime;

import constants.Constants;

public class DateRange {
    private LocalDateTime start, end;

    public DateRange(LocalDateTime start, LocalDateTime end) throws DukeException {
        this.start = start;
        this.end = end;
        if (isInvalid()) {
            throw new InvalidDateException("Start date cannot be later than end date.");
        }
    }

    public static DateRange createFromString(String start, String end) throws DukeException {
        return new DateRange(LocalDateTime.parse(start, Constants.Input.DATETIME_FORMATTER),
                LocalDateTime.parse(end, Constants.Input.DATETIME_FORMATTER));
    }

    /**
     * Returns a DateRange object created from a single string representing the
     * datetime range. The string can of the following formats: dd/MM/yyyy
     * HHmm-dd/MM/yyyy HHmm or dd/MM/yyyy HHmm-HHmm The latter occurs on a single
     * day.
     * 
     * @param range String representing a range of dates.
     * 
     */
    public static DateRange createFromRange(String range) throws DukeException {
        String[] rangeArr = range.split(Constants.Input.DATE_RANGE_SEPARATOR);
        String start = rangeArr[0];
        String datePart = rangeArr[0].split(" ")[0];
        String end = rangeArr[1];
        if (!end.contains(" ")) {
            // Here end only has the time part, so we append the date part
            end = String.format("%s %s", datePart, end);
        }

        return createFromString(start, end);
    }

    public boolean isValid() {
        return !isInvalid();
    }

    public boolean isInvalid() {
        return start.isAfter(end);
    }

    public boolean occursBefore(LocalDateTime dateTime) {
        return start.isAfter(dateTime);
    }

    public boolean occursDuringDay(LocalDate date) {
        return !start.toLocalDate().isAfter(date) && !end.toLocalDate().isBefore(date);
    }

    public boolean occursBetween(LocalDateTime dateTime) {
        return !start.isAfter(dateTime) && !end.isBefore(dateTime);
    }

    public boolean occursStrictlyBetween(LocalDateTime dateTime) {
        return start.isBefore(dateTime) && end.isAfter(dateTime);
    }

    public boolean occursAfter(LocalDateTime dateTime) {
        return end.isBefore(dateTime);
    }

    @Override
    public String toString() {
        String start = Constants.Display.DATETIME_FORMATTER.format(this.start);
        String end = Constants.Display.DATETIME_FORMATTER.format(this.end);
        return String.format("between %s and %s", start, end);
    }
}
