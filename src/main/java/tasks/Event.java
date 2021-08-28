package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String description, LocalDate startTime, LocalDate endTime) {
        super(description, "[E]", false);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String description, LocalDate startTime, LocalDate endTime, boolean status) {
        super(description, "[E]", status);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getFormattedData() {
        String formattedStatus = super.isDone() ? "1|" : "0|";
        return "E|" + formattedStatus + super.getDescription()
                + "|" + this.startTime.toString() 
                + "|" + this.endTime.toString();
    }

    @Override
    public String toString() {
        return super.toString() + " (from " 
                + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) 
                + " to " + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
