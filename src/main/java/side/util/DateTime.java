package side.util;

import side.exception.WrongDatetimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

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

    public DateTime(String date) {
        try {
            this.localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new WrongDatetimeException();
        }
        this.localTime = null;
        this.localDateTime = null;
    }


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

    public String getDatetime() {
        if (localDateTime != null) {
            return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(localDateTime);
        } else {
            return DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(localDate);
        }
    }

    public String saveDatetime() {
        if (localDateTime != null) {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd, HHmm").format(localDateTime);
        } else {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
        }
    }

    @Override
    public String toString() {
        return this.getDatetime();
    }
}