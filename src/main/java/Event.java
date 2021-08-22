import java.time.LocalDate;

public class Event extends Task {
    LocalDate time;

    public Event(String taskName, LocalDate time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }
}