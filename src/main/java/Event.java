import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description, "E");
        this.at = at;
    }

    @Override
    public String toString() {
        String month = this.at.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        int date = this.at.getDayOfMonth();
        int year = this.at.getYear();

        String toPrint = String.format("[E]%s (at: %s %d %d)", super.toString(), month, date, year);
        return toPrint;
    }

    @Override
    public String markDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n  [E][X] %s", this.description);
    }
}
