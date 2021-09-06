package duke.formatter;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * An object that creates a custom DateTime format that will be used by the Tasks.
 */
public class DateTimeFormatCreator {

    /**
     * A class level method that returns a DateTimeFormatter which allows for a custom DateTime format.
     * @return DateTimeFormatter
     */
    public static DateTimeFormatter format() {
        return new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd[ HH:mm[:ss]]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
    }
}
