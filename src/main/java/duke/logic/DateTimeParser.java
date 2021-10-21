package duke.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.exception.DukeException;


/**
 * Enables simpler parsing of date and time for events and deadlines.
 */
public class DateTimeParser {
    private final LocalTime time;
    private final LocalDate date;

    /**
     * Creates a new instance of a Date.
     *
     * @param dateTime The string to be parsed
     */
    public DateTimeParser(String dateTime) {
        this(dateTime, LocalDate.now(), LocalTime.now());
    }

    /**
     * Creates a new instance of a Date. This is used when there is a different start date and time.
     *
     * @param dateTime          the string to be parsed
     * @param relativeStartDate the relative starting date of the task (by default now)
     * @param relativeStartTime the relative starting time of the task (by default now)
     */
    public DateTimeParser(String dateTime, LocalDate relativeStartDate, LocalTime relativeStartTime) {
        String[] dateAndTime = Arrays.stream(dateTime.split("[ |,]", 2))
            .map(String::trim).toArray(String[]::new);
        try {
            if (dateAndTime.length == 1) {
                if (dateAndTime[0].contains("/")) { // User entered date
                    time = LocalTime.parse("23:59");
                } else { // User likely entered time
                    time = parseTime(dateAndTime[0]);
                    date = time.isAfter(relativeStartTime) ? relativeStartDate : relativeStartDate.plusDays(1);
                    return;
                }
            } else {
                time = parseTime(dateAndTime[1]);
            }
            date = parseDate(dateAndTime[0]);

        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date and time format. Please enter them in the format: d/M/YYYY H:m.\n"
                + "For example: 23/8/2021 14:00");
        }
    }

    /**
     * Returns the LocalDateTime object associated with the date and time represented in the data string.
     *
     * @param data the string containing the date and time of the task. Format is "yyyy-MM-dd HH:mm".
     * @return the LocalDateTime object
     */
    public static LocalDateTime getDateTimeFromDataString(String data) {
        return LocalDateTime.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy"));
    }

    private LocalTime parseTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("H:m"));
    }

    /**
     * Gets the date of this datetime.
     *
     * @return the local date object.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the time of this datetime.
     *
     * @return the local time object.
     */
    public LocalTime getTime() {
        return time;
    }
}
