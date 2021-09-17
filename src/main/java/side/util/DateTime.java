package side.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import side.exception.WrongDatetimeException;

/**
 * Encapsulates the time and date for tasks, and corresponding conversion from string to
 * [yyyy-MM-dd] and [HHmm].
 *
 * It encapsulates the following information:
 * - localDate
 * - localTime
 * - localDateTime
 *
 * @author Lua Yi Da
 */

public class DateTime {

    private LocalDate localDate;
    private LocalTime localTime;
    private LocalDateTime localDateTime;

    /**
     * Initialises a new DateTime.
     *
     * @param date String representing date to be converted.
     */
    public DateTime(String date) {
        try {
            this.localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new WrongDatetimeException();
        }
        this.localTime = null;
        this.localDateTime = null;
    }

    /**
     * Initialises a new Datetime.
     *
     * @param date String representing date to be converted.
     * @param time String representing time to be converted.
     */
    public DateTime(String date, String time) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            this.localDate = LocalDate.parse(date, dateFormatter);
            this.localTime = LocalTime.parse(time, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new WrongDatetimeException();
        }
        this.localDateTime = localDate.atTime(localTime);
    }

    /**
     * Gets string of datetime.
     *
     * @return String representing datetime.
     */
    public String getDatetime() {
        if (localDateTime != null) {
            return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(localDateTime);
        } else {
            return DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(localDate);
        }
    }

    /**
     * Gets string of datetime formatted for saving.
     *
     * @return String representing datetime.
     */
    public String saveDatetime() {
        if (localDateTime != null) {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd, HHmm").format(localDateTime);
        } else {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
        }
    }

    /**
     * Overrides toString to format DateTime for list.
     *
     * @return String representing DateTime formatted.
     */
    @Override
    public String toString() {
        return this.getDatetime();
    }
}
