package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task{
    private LocalDate date;
    private LocalTime time;

    public Event(String description, String deadline) {
        super(description, "[E]");
        this.date = LocalDate.parse(deadline.substring(0,10));
        this.time = LocalTime.parse(deadline.substring(11), DateTimeFormatter.ofPattern("HHmm"));
    }

    public Event(String description, String date, String time) {
        super(description, "[E]");
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("h:ma"));
    }

    @Override
    public String getDescription() {
        return super.getDescription()
                + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + this.time.format(DateTimeFormatter.ofPattern(" h:mma"))
                + ")";
    }
}
