import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected String description;
    protected boolean isDone;
    final String EVENT = "[E]";
    protected String dateAndTime;
    protected LocalDateTime localDateTime;

    public Event(String description, String dateAndTime) {
        super(description);
        this.description = description;
        this.isDone = false;
        this.dateAndTime = dateAndTime;
    }

    public void formatLocalDateTime() {
        int space = dateAndTime.lastIndexOf(" ");
        String date = dateAndTime.substring(0, space);
        String time = dateAndTime.substring(space + 1);
        this.localDateTime = LocalDateTime.parse(date + "T" + time);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd YYYY HH:mm");
        formatLocalDateTime();
        return EVENT + this.getStatusIcon() + " " + this.getDescription() + " (at: " + localDateTime.format(dtf) + ")";
    }
}
