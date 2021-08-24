import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description, "D");
        this.by = by;
    }

    @Override
    public String toString() {
        String month = this.by.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        int date = this.by.getDayOfMonth();
        int year = this.by.getYear();

        String toPrint = String.format("[D]%s (by: %s %d %d)", super.toString(), month, date, year);
        return toPrint;
    }

    @Override
    public String markDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n  [D][X] %s", this.description);
    }
}
