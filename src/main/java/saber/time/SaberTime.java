package saber.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import saber.exceptions.SaberTimeParserException;

/**
 * Encapsulates a time.
 */
public class SaberTime {
    private static final DateTimeFormatter DATE_FORMAT_WITH_HOURS_FOR_DISPLAY =
            DateTimeFormatter.ofPattern("E, MMM d yyyy HH:mm");
    private static final DateTimeFormatter DATE_FORMAT_WITHOUT_HOURS_FOR_DISPLAY =
            DateTimeFormatter.ofPattern("E, MMM d yyyy");

    /** A list of accepted date formats (with hours) so Saber can understand more
     * than one date format
     */
    private static final DateTimeFormatter[] DATE_FORMATTERS_WITH_HOURS = {
        DATE_FORMAT_WITH_HOURS_FOR_DISPLAY,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
        DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm"),
        DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"),
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
        DateTimeFormatter.ofPattern("MMM, d yyyy HH:mm"),
        DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm"),
        DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"),
    };

    /** A list of accepted date formats (without hours) so Saber can understand more
     * than one date format
     */
    private static final DateTimeFormatter[] DATE_FORMATTERS_WITHOUT_HOURS = {
        DATE_FORMAT_WITHOUT_HOURS_FOR_DISPLAY,
        DateTimeFormatter.ofPattern("yyyy-MM-dd"),
        DateTimeFormatter.ofPattern("dd/MM/yy"),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy"),
        DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy"),
        DateTimeFormatter.ofPattern("MMM, d yyyy"),
        DateTimeFormatter.ofPattern("d MMMM yyyy"),
        DateTimeFormatter.ofPattern("d MMM yyyy")
    };

    protected LocalDate localDate;
    protected LocalDateTime localDateTime;

    /**
     * Constructs SaberTime. This constructor also parse the time to Java's LocalDate or LocalDateTime
     * to ensure that the date passed are in recognizable date formats
     *
     * @param time the raw unparsed time
     * @throws SaberTimeParserException if an error occurs during the parsing of the time
     */
    public SaberTime (String time) throws SaberTimeParserException {
        for (DateTimeFormatter df : DATE_FORMATTERS_WITH_HOURS) {
            try {
                this.localDateTime = LocalDateTime.parse(time, df);
                break;
            } catch (DateTimeParseException e) {
                // Ignore; try next formatter
            }
        }
        for (DateTimeFormatter df : DATE_FORMATTERS_WITHOUT_HOURS) {
            try {
                this.localDate = LocalDate.parse(time, df);
                break;
            } catch (DateTimeParseException e) {
                // Ignore; try next formatter
            }
        }

        // Throw an exception if all prescribed date format fails
        if (this.localDate == null && this.localDateTime == null) {
            throw new SaberTimeParserException("Unable to parse time");
        }
    }

    /**
     * Returns the string representation of time
     *
     * @return String representation of time
     */
    @Override
    public String toString() {
        String time;
        if (localDateTime != null) {
            time = localDateTime.format(DATE_FORMAT_WITH_HOURS_FOR_DISPLAY);
        } else {
            time = localDate.format(DATE_FORMAT_WITHOUT_HOURS_FOR_DISPLAY);
        }
        return time;
    }

}
