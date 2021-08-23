import java.time.LocalDate;

public class Event extends Task {
    private LocalDate date;

    public Event(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public boolean isToday() {
        return date.equals(LocalDate.now());
    }

    @Override
    public boolean isExpired() {
        return date.isBefore(LocalDate.now());
    }

    // prints in red if the event is passed, yellow if event is today
    @Override
    public String toString() {
        String color = isDone()
                ? ""
                : isToday()
                ? StringFormatter.ANSI_YELLOW
                : isExpired()
                ? StringFormatter.ANSI_RED
                : "";

        return color +
                "[E] " + super.toString() + " (at: " + date + ")" +
                (isToday() || isExpired() ? StringFormatter.ANSI_RESET : "");
    }
}
