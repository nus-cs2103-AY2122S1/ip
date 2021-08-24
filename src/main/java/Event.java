import java.time.LocalDate;

public class Event extends Task {
    private LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s %d %d)", super.toString(), date.getMonth(), date.getDayOfMonth(),
                date.getYear());
    }
}
