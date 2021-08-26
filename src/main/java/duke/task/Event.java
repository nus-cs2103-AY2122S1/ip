package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }


    @Override
    public String stringifyTask() {
        return String.format("E|%d|%s|%s|%s", this.isDone ? 1 : 0, this.description,
                this.startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                this.endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }


    @Override
    public String toString() {
        return String.format("[E]%s (from %s to %s)", super.toString(),
                this.startTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                this.endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}
