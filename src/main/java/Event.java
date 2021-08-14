import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;

    Event(String name, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(name);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    Event(String name, LocalDate date, LocalTime startTime, LocalTime endTime, boolean isDone) {
        super(name, isDone);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String startTimeString = startTime.format(DateTimeFormatter.ofPattern("h:mm a"));
        String endTimeString = endTime.format(DateTimeFormatter.ofPattern("h:mm a"));
        return "[E]" + super.toString() + " (at: " + dateString + " " + startTimeString + " - " + endTimeString + ")";

    }
}
