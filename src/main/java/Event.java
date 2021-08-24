import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private DateTimeFormatter printOut = DateTimeFormatter.ofPattern("MMM dd, E, yyyy");
    protected LocalDate time;

    public Event(String description, LocalDate time) {
        super(description, Task.Type.E);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + printOut.format(time) + ")";
    }
}
