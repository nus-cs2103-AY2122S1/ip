package task;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getDescription(){
        return super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    public LocalDate getDate() {
        return this.at.toLocalDate();
    }

    public LocalTime getTime() {
        return this.at.toLocalTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}