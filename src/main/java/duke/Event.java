package duke;

import java.time.LocalDateTime;

public class Event extends Task{
    private LocalDateTime date;
    public Event(String event, LocalDateTime date) {
        super(event, "E", date);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (at: %s)",
                this.getTaskSymbol(), super.toString(),dateToString(this.date));
    }
}
