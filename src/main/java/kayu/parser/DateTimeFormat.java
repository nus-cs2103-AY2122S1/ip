package kayu.parser;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * DateTimeFormat class.
 * 
 * This class helps as a utility for {@link kayu.parser.Parser} 
 * and {@link kayu.commands.Command} in parsing DateTime String representations.
 */
public class DateTimeFormat {
    
    private final List<DateTimeFormatter> dateFormats = new ArrayList<>();
    
    private final List<DateTimeFormatter> timeFormats = new ArrayList<>();

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
    
    // private to allow construction through public static method
    private DateTimeFormat() {}
    
    private void initializeFormats() {
        initializeDateFormats();
        initializeTimeFormats();
    }

    private void initializeDateFormats() {
        dateFormats.clear();
        
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy-M-dd"));
        dateFormats.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        dateFormats.add(DateTimeFormatter.ofPattern("dd-M-yyyy"));
        
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy/M/dd"));
        dateFormats.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateFormats.add(DateTimeFormatter.ofPattern("dd/M/yyyy"));
    }

    private void initializeTimeFormats() {
        timeFormats.clear();
        
        timeFormats.add(DateTimeFormatter.ofPattern("HH:mm"));
        timeFormats.add(DateTimeFormatter.ofPattern("HHmm"));

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
