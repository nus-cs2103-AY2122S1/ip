import java.time.LocalDate;

public class Event extends Task {
    private LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    };

    public LocalDate getTime() {
        return this.time;
    }
}
