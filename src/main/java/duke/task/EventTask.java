package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task{
    LocalDateTime date;

    public EventTask(String content, boolean isDone, LocalDateTime date) {
        super(content, isDone);
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (at: %s)",
                super.toString(),
                this.date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm")));
    }
}
