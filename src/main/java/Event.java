import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate time;

    public Event(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time);
    }

    private String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return this.time.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.getTime());
    }
}
