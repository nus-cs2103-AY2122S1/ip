import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    public Event(String description, String by) {
        super(description);
        this.at = LocalDateTime.parse(by, formatter);
    }

    public Event(String num, String description, String at) {
        this(description, at);
        this.isDone = !num.equals("0");
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String getFileString() {
        return String.format("E | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.at);
    }


    @Override
    public String toString() {
        String formattedAt = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
        return "[E]" + super.toString() + " (at: " + formattedAt + ")";
    }
}