import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    LocalDate startTime;
    LocalDate endTime;

    public Event(String description, LocalDate startTime, LocalDate endTime) {
        super(description, "[E]");
        this.startTime = startTime;
        this.endTime = endTime;
    }


    @Override
    public String toString() {
        return super.toString() + " (from " 
                + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) 
                + " to " + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
