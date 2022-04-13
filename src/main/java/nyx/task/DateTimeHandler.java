package nyx.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import nyx.NyxException;

interface DateTimeHandler {
    String DATETIME_FORMAT = "yyyy-MM-dd H:m";

    static LocalDateTime parseDateTime(String dateTime) throws NyxException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new NyxException("Incorrect datetime format! The correct format is YYYY-MM-DD H:m");
        }
    }
}
