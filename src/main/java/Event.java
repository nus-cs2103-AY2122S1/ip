import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate timePeriod;

    public Event(String taskname, String timePeriod) {
        super(taskname);
        this.timePeriod = LocalDate.parse(timePeriod);
    }

    @Override
    public String toString() {
        return "Event: " + super.toString() + " (at " + this.timePeriod.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
