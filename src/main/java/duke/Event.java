package duke;
import java.time.format.DateTimeFormatter;

public class Event extends duke.Task {

    private String date;
    private DateTimeFormatter test;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + date + ")";
    }
}
