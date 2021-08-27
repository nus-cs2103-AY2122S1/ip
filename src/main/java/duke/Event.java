package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime time;

    public Event(String task, LocalDateTime time) {
        super(task, "E");
        this.time = time;
    }

    public Event(String task, boolean completed, LocalDateTime time) {
        super(task, completed, "E");
        this.time = time;
    }

    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", this.getTaskType(), this.getCompletedMarker(),
                this.getTask(), this.getTime());
    }

    @Override
    public String parseForStorage() {
        return String.format("%s | %d | %s | %s", this.getTaskType(), this.getIsCompleted() ? 1 : 0,
                this.getTask(), this.getTime());
    }
}
