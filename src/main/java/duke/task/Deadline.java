package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public Deadline(String description, boolean isDone, LocalDateTime dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Encode Deadline in format
     * "taskType isDone description dateTime"
     * @return encoded event string
     */
    public String encode() {
        return String.format("D %b %s /by %s",
                isDone,
                content,
                dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
        );
    }

    public String toString() {
        return String.format("[D] [%s] %s (by: %s)",
                isDone ? "X" : " ", 
                content, 
                dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }
}
