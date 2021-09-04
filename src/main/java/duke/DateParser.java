package duke;

import exceptions.DukeInvalidDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class that contains functions used to parse formatted strings to dates.
 */
class DateParser {

    /**
     * The date formatters used by duke to format a date to a string to display to the user or to store
     * in the local file taskList.txt.
     * */
    protected static final DateTimeFormatter PRINT_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("d MMM yyyy");
    protected static final DateTimeFormatter PRINT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("d MMM yyyy HHmm");

    /** The date formatters used by duke.Duke to format date strings provided by the user. */
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/[uuuu][uu]");
    private static final DateTimeFormatter DATE_FORMATTER_WITH_TIME =
            DateTimeFormatter.ofPattern("d/M/[uuuu][uu] HHmm");

    /**
     * Returns a LocalDate based on a formatted String containing a date. The string should have
     * the format dd/MM/yyyy. A two digit year or a 1 digit date and month would be accepted as well.
     *
     * @param dateString The formatted String.
     * @return The LocalDate object converted from the string.
     * @throws DukeInvalidDateException Throws an exception when the dateString cannot be parsed.
     */
    protected static LocalDate parseDate(String dateString) throws DukeInvalidDateException {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException();
        }
    }

    /**
     * Returns a LocalDateTime based on a formatted String containing a date. The string should have
     * the format dd/MM/yyyy HHmm. A two digit year or a 1 digit date and month would be accepted as well.
     *
     * @param dateTimeString The formatted String.
     * @return The LocalDateTime object converted from the string.
     * @throws DukeInvalidDateException Throws an exception when the dateTimeString cannot be parsed.
     */
    protected static LocalDateTime parseDateTime(String dateTimeString) throws DukeInvalidDateException {
        try {
            return LocalDateTime.parse(dateTimeString, DATE_FORMATTER_WITH_TIME);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException();
        }
    }
}
