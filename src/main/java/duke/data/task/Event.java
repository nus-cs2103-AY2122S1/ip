package duke.data.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    private final DateTimeFormatter input = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    private final DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd h:mm a");

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String dateFormatter(String at) {
        LocalDateTime date = LocalDateTime.parse(at, input);
        return date.format(output);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateFormatter(at) + ")";
    }
}
