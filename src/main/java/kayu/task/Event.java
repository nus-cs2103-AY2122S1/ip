package kayu.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event class.
 *
 * This class is a Task that has an 'at' datetime String.
 */
public class Event extends Task {

    public final static String KEYWORD = "E";

    public final static String SPLIT_WORD = "at";

    private final LocalDate atDate;
    
    private final LocalTime atTime;

    public Event(String description, boolean isDone, LocalDate atDate, LocalTime atTime) {
        super(description, isDone);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    public Event(String description, LocalDate atDate, LocalTime atTime) {
        super(description);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    public LocalDate getAtDate() {
        return atDate;
    }

    public LocalTime getAtTime() {
        return atTime;
    }

    /**
     * Provides a formatted String of the 'at' field.
     *
     * @return formatted String for field 'at'
     */
    private String getFormattedAt() {
        return "(" 
                + SPLIT_WORD 
                + ": " 
                + atDate 
                + " " + atTime
                + ")";
    }

    @Override
    public String toEncodedString() {
        return KEYWORD 
                + SPLIT_TEMPLATE
                + super.toEncodedString() 
                + SPLIT_TEMPLATE
                + atDate
                + SPLIT_TEMPLATE 
                + atTime;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + ' ' + getFormattedAt();
    }
}
