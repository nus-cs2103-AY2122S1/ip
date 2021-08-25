package duke.logic;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Enables simpler parsing of date and time for events and deadlines.
 */
public class LDateTimeParser {
    private final LocalTime time;
    private final LocalDate date;

    /**
     * Creates a new instance of a Date
     *
     * @param dateTime The string to be parsed
     */
    public LDateTimeParser(String dateTime) {
        String[] dateAndTime = Arrays.stream(dateTime.split("[ |,]", 2))
                .map(String::trim).toArray(String[]::new);
        try {
            if (dateAndTime.length == 1) {
                if (dateAndTime[0].contains("/")) { // User entered date
                    date = LocalDate.parse(dateAndTime[0], DateTimeFormatter.ofPattern("d/M/yyyy"));
                    time = LocalTime.parse("23:59");
                } else { // User likely entered time
                    time = LocalTime.parse(dateAndTime[0], DateTimeFormatter.ofPattern("H:m"));
                    date = time.isAfter(LocalTime.now()) ? LocalDate.now() : LocalDate.now().plusDays(1);
                }
            } else {
                date = LocalDate.parse(dateAndTime[0], DateTimeFormatter.ofPattern("d/M/yyyy"));
                time = LocalTime.parse(dateAndTime[1], DateTimeFormatter.ofPattern("H:m"));
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date and time format. Please enter them in the format: d/M/YYYY H:m.\n" +
                    "For example: 23/8/2021 14:00");
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}