import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class that contains functions used to parse formatted strings to dates.
 */
public class DateParser {

    /** The strings format accepted by duke for used as a DukeDate. */
    private static final String DATE_FORMAT = "d/M/[uuuu][uu]";
    private static final String DATE_FORMAT_WITH_TIME = "d/M/[uuuu][uu] HHmm";

    /**
     * Returns a LocalDate based on a formatted String containing a date. The string should have
     * the format dd/MM/yyyy. A two digit year or a 1 digit date and month would be accepted as well.
     *
     * @param dateString The formatted String.
     * @return The LocalDate object converted from the string.
     * @throws DukeInvalidDateException Throws an exception when the dateString cannot be parsed.
     */
    public static LocalDate parseDate(String dateString) throws DukeInvalidDateException {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            return LocalDate.parse(dateString, dateFormatter);
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
    public static LocalDateTime parseDateTime(String dateTimeString) throws DukeInvalidDateException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_WITH_TIME);
            return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException();
        }
    }
}
