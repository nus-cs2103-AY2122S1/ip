package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Event extends Task {
    private final LocalDate date;

    Event(String body, LocalDate date) {
        super(body, false);
        this.date = date;
    }
    Event(String body, boolean done, LocalDate date) {
        super(body, done);
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    Task setDone() {
        return new Event(this.getBody(), true, this.date);
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return "[E] [X]"
                    + this.getBody()
                    + " (at: "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + ")";
        } else {
            return "[E] [ ]"
                    + this.getBody()
                    + " (at: "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + ")";
        }
    }
}
