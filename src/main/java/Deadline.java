import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String name, LocalDate date) {
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

    // prints in red if the deadline is expired, yellow if deadline is today
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
                "[D] " + super.toString() + " (by: " + date + ")" +
                (isToday() || isExpired() ? StringFormatter.ANSI_RESET : "");
    }
}
