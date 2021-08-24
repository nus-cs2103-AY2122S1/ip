package duke.task;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate date;

    public Event(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toDataString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, this.description, this.date);
    }
    
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s %d %d)", super.toString(), date.getMonth(), date.getDayOfMonth(),
                date.getYear());
    }
}
