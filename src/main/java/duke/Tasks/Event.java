package duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private TaskType type = TaskType.EVENT;
    private LocalDateTime timeAt;

    public Event (String description, String timeAt){
        super(description);
        this.timeAt = LocalDateTime.parse(timeAt, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public Event (String description, String timeAt, boolean isDone) {
        super(description, isDone);
        this.timeAt = LocalDateTime.parse(timeAt, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String showTask() {
        return "[E][" + (isDone ? "✗" : " ") + "] " + description + " (at: " + timeAt.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
    }

    @Override
    public String saveTask() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + timeAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}