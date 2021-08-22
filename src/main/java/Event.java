import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    
    private final boolean hasTime;
    private final boolean hasEndDate;

    public Event(String taskName, LocalDate startDate, LocalTime startTime, LocalTime endTime) {
        super(taskName);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        hasTime = true;
        hasEndDate = false;
    }

    public Event(String taskName, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        hasTime = true;
        hasEndDate = true;
    }

    public Event(String taskName, LocalDate startDate, LocalDate endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
        hasTime = false;
        hasEndDate = true;
    }

    @Override
    public String listEntry() {
        if (hasTime) {
            if (hasEndDate) {
                return "[E]" + super.listEntry() 
                        + " (from " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) 
                        + " at " + startTime.format(DateTimeFormatter.ofPattern("h:mm a"))
                        + " to " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + " at " + endTime.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
            } else {
                return "[E]" + super.listEntry()
                        + " (on " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + " from " + startTime.format(DateTimeFormatter.ofPattern("h:mm a"))
                        + " to " + endTime.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
            }
        } else {
            return "[E]" + super.listEntry()
                    + " (from " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " to " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
}
