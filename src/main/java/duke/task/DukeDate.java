package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a date in Duke. Can be any arbitrary string or an actual date.
 */
public abstract class DukeDate {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Returns a {@link DukeDate} representing the given string. The given date
     * is converted to a {@link java.util.Date} type, if possible. Otherwise, a string representation
     * is returned.
     *
     * @param date the date as a string
     * @return a {@link DukeDate} representing the given string
     */
    public static DukeDate of(String date) {
        try {
            return new DukeLocalDate(LocalDate.parse(date));
        } catch (DateTimeParseException e) {
            return new DukeStringDate(date);
        }
    }

    private static class DukeStringDate extends DukeDate {
        private final String date;

        private DukeStringDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return date;
        }
    }

    private static class DukeLocalDate extends DukeDate {
        private final LocalDate date;

        private DukeLocalDate(LocalDate date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return date.format(DATE_TIME_FORMATTER);
        }
    }
}
