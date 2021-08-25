package duke.commands;

import duke.commands.Task;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description, "E");
        this.at = at;
    }

    public LocalDateTime getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        String month = this.at.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        int date = this.at.getDayOfMonth();
        int year = this.at.getYear();
        int hour = this.at.getHour();
        int minute = this.at.getMinute();

        String toPrint = String.format("[E]%s (at: %s %d %d %s:%s)",
                super.toString(), month, date, year, hour, minute);
        return toPrint;
    }

    @Override
    public String markDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n  [E][X] %s", this.description);
    }
}
