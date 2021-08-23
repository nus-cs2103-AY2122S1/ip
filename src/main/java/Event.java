import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Tasks that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    private boolean isTimeString;
    private String timeString;
    private LocalDate startDate; 
    private LocalTime startTime;

    public Event(String name, String time) {
        super(name);
        this.timeString = time;
        this.isTimeString = true;
    }

    public Event(String name, LocalDate startDate) {
        super(name);
        this.startDate = startDate;
        this.isTimeString = false; 
    }

    public Event(String name, LocalDate startDate, LocalTime startTime) {
        super(name);
        this.startDate = startDate;
        this.startTime = startTime;
        this.isTimeString = false; 
    }

    private String getTime() {
        if (isTimeString) {
            return timeString; 
        } else if (startTime == null) {
            return Processor.OUT_DATE_FORMATTER.format(startDate);
        } else {
            return Processor.OUT_DATE_FORMATTER.format(startDate) + " " + Processor.OUT_TIME_FORMATTER.format(startTime);
        }
    }

    public String toString() {
        if (isDone) {
            return "[E][X] " + name + " (at: " + getTime() + ")"; 
        } else {
            return "[E][ ] " + name + " (at: " + getTime() + ")"; 
        }
    }
}