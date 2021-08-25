package duke.commands;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, "D");
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        String month = this.by.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        int date = this.by.getDayOfMonth();
        int year = this.by.getYear();
        int hour = this.by.getHour();
        int minute = this.by.getMinute();

        String toPrint = String.format("[D]%s (at: %s %d %d %s:%s)",
                super.toString(), month, date, year, hour, minute);
        return toPrint;
    }

    @Override
    public String markDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n  [D][X] %s", this.description);
    }
}
