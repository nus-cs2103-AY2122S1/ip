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
    
    private final List<DateTimeFormatter> dateFormatterList = new ArrayList<>();
    
    private final List<DateTimeFormatter> timeFormatterList = new ArrayList<>();

    /**
     * Generates an instance of this class, as well as initialise its fields.
     * 
     * @return A DateTimeFormat class with initialised fields.
     */
    public static DateTimeFormat generate() {
        DateTimeFormat dateTimeFormat = new DateTimeFormat();
        dateTimeFormat.init();
        return dateTimeFormat;
    }
    
    // private to allow construction through public static method
    private DateTimeFormat() {}
    
    private void init() {
        initialiseDateFormats();
        initialiseTimeFormats();
    }

    private void initialiseDateFormats() {
        dateFormatterList.clear();
        
        dateFormatterList.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("yyyy-M-dd"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("dd-M-yyyy"));
        
        dateFormatterList.add(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("yyyy/M/dd"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("dd/M/yyyy"));
    }

    private void initialiseTimeFormats() {
        timeFormatterList.clear();
        
        timeFormatterList.add(DateTimeFormatter.ofPattern("HH:mm"));
        timeFormatterList.add(DateTimeFormatter.ofPattern("HHmm"));

        timeFormatterList.add(DateTimeFormatter.ofPattern("hh:mm a"));
        timeFormatterList.add(DateTimeFormatter.ofPattern("hhmm a"));
    }

    /**
     * Returns a List of date formats as {@link DateTimeFormatter}.
     * 
     * @return A list of date formats as {@link DateTimeFormatter}.
     */
    public List<DateTimeFormatter> getDateFormatterList() {
        return dateFormatterList;
    }

    /**
     * Returns a List of time formats as {@link DateTimeFormatter}.
     *
     * @return A list of time formats as {@link DateTimeFormatter}.
     */
    public List<DateTimeFormatter> getTimeFormatterList() {
        return timeFormatterList;
    }
}
