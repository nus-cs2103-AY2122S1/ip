import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDate eventDate;

    public Event (String todoName) {
        super(todoName.substring(6, todoName.indexOf("/at")));
        int start = todoName.indexOf("/at");
        this.eventDate = LocalDate.parse(todoName.substring(start + 4));
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + "(at: "
                + this.eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
