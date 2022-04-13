package kayu.parser;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains DateTime formats for {@link kayu.parser.Parser} and {@link kayu.commands.Command}.
 */
public class DateTimeFormat {

    // Lists containing formats.
    private final List<DateTimeFormatter> dateFormats = new ArrayList<>();
    private final List<DateTimeFormatter> timeFormats = new ArrayList<>();

    // Private to allow construction through public static method.
    private DateTimeFormat() {}

    /**
     * Generates an instance of this class, as well as initialise its fields.
     *
     * @return A DateTimeFormat class with initialised fields.
     */
    public static DateTimeFormat generateInstance() {
        DateTimeFormat dateTimeFormat = new DateTimeFormat();
        dateTimeFormat.initializeFormats();
        return dateTimeFormat;
    }

    private void initializeFormats() {
        initializeDateFormats();
        initializeTimeFormats();
    }

    private void initializeDateFormats() {
        dateFormats.clear();

        // dates with dash -
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy-M-dd"));
        dateFormats.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        dateFormats.add(DateTimeFormatter.ofPattern("dd-M-yyyy"));

        // dates with slash /
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy/M/dd"));
        dateFormats.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateFormats.add(DateTimeFormatter.ofPattern("dd/M/yyyy"));
    }

    private void initializeTimeFormats() {
        timeFormats.clear();

        // 24 hour formats
        timeFormats.add(DateTimeFormatter.ofPattern("HH:mm"));
        timeFormats.add(DateTimeFormatter.ofPattern("HHmm"));

        // 12 hour formats
        timeFormats.add(DateTimeFormatter.ofPattern("hh:mm a"));
        timeFormats.add(DateTimeFormatter.ofPattern("hhmm a"));
    }

    /**
     * Returns a List of date formats as {@link DateTimeFormatter}.
     *
     * @return A list of date formats as {@link DateTimeFormatter}.
     */
    public List<DateTimeFormatter> getDateFormats() {
        return dateFormats;
    }

    /**
     * Returns a List of time formats as {@link DateTimeFormatter}.
     *
     * @return A list of time formats as {@link DateTimeFormatter}.
     */
    public List<DateTimeFormatter> getTimeFormats() {
        return timeFormats;
    }
}
